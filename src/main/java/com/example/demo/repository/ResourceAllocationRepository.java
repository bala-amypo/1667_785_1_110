package com.example.demo.repository;

import com.example.demo.entity.ResourceAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ResourceAllocationRepository extends JpaRepository<ResourceAllocation, Long> {
    List<ResourceAllocation> findByResourceTypeAndStartTimeBetween(
        String resourceType, LocalDateTime start, LocalDateTime end
    );
}
