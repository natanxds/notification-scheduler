package com.natanxds.Microservice.repository;

import com.natanxds.Microservice.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
