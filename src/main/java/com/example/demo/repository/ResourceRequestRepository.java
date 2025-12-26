package com.example.demo.repository;

import com.example.demo.entity.ResourceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ResourceRequestRepository extends JpaRepository<ResourceRequest, Long> {

    // Get requests by user ID
    List<ResourceRequest> findByRequestedBy_Id(Long userId);

    // Get requests between start times
    List<ResourceRequest> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
