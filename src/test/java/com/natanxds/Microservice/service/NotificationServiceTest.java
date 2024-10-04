package com.natanxds.Microservice.service;

import com.natanxds.Microservice.dto.ScheduleNotificationDto;
import com.natanxds.Microservice.entity.Notification;
import com.natanxds.Microservice.entity.Status;
import com.natanxds.Microservice.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    @Captor
    private ArgumentCaptor<Notification> notificationCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should save notification when scheduling")
    void scheduleNotification_savesNotification() {
        ScheduleNotificationDto dto = mock(ScheduleNotificationDto.class);
        Notification notification = new Notification();
        when(dto.toNotification()).thenReturn(notification);

        notificationService.scheduleNotification(dto);

        verify(notificationRepository).save(notification);
    }

    @Test
    @DisplayName("Should return notification when found by ID")
    void findById_returnsNotificationWhenFound() {
        Notification notification = new Notification();
        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notification));

        Optional<Notification> result = notificationService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(notification, result.get());
    }

    @Test
    @DisplayName("Should return empty when notification not found by ID")
    void findById_returnsEmptyWhenNotFound() {
        when(notificationRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Notification> result = notificationService.findById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Should set status to canceled when canceling notification")
    void cancelNotification_setsStatusToCanceled() {
        Notification notification = new Notification();
        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notification));

        notificationService.cancelNotification(1L);

        verify(notificationRepository).save(notificationCaptor.capture());
        assertEquals(Status.Values.CANCELED.toStatus(), notificationCaptor.getValue().getStatus());
    }

    @Test
    @DisplayName("Should do nothing when canceling notification that is not found")
    void cancelNotification_doesNothingWhenNotFound() {
        when(notificationRepository.findById(1L)).thenReturn(Optional.empty());

        notificationService.cancelNotification(1L);

        verify(notificationRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should send pending and error notifications")
    void checkAndSend_sendsPendingAndErrorNotifications() {
        Notification pendingNotification = new Notification();
        pendingNotification.setStatus(Status.Values.PEDING.toStatus());
        Notification errorNotification = new Notification();
        errorNotification.setStatus(Status.Values.ERROR.toStatus());
        when(notificationRepository.findByStatusInAndDateTimeBefore(anyList(), any(LocalDateTime.class)))
                .thenReturn(List.of(pendingNotification, errorNotification));

        notificationService.checkAndSend(LocalDateTime.now());

        verify(notificationRepository, times(2)).save(notificationCaptor.capture());
        List<Notification> savedNotifications = notificationCaptor.getAllValues();
        assertEquals(Status.Values.SUCCESS.toStatus(), savedNotifications.get(0).getStatus());
        assertEquals(Status.Values.SUCCESS.toStatus(), savedNotifications.get(1).getStatus());
    }
}