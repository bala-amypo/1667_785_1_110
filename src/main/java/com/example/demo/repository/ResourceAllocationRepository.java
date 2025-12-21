package com.example.demo.repository;

import com.example.demo.entity.ResourceAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ResourceAllocationRepository extends JpaRepository<ResourceAllocation, Long> {

    // Find allocations by the user who requested them
    List<ResourceAllocation> findByRequestedBy_Id(Long userId);

    // Find allocations of a specific resource type within a time range
    List<ResourceAllocation> findByResourceTypeAndStartTimeBetween(
            String resourceType, LocalDateTime startTime, LocalDateTime endTime
    );
}
