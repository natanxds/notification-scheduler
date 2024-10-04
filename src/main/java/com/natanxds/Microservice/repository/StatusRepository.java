package com.natanxds.Microservice.repository;

import com.natanxds.Microservice.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
