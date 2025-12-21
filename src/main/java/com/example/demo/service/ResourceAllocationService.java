package com.example.demo.service;

import com.example.demo.entity.ResourceAllocation;
import java.time.LocalDateTime;
import java.util.List;

public interface ResourceAllocationService {

    ResourceAllocation createAllocation(ResourceAllocation allocation);

    List<ResourceAllocation> getAllocationsByResourceTypeAndTime(String resourceType, LocalDateTime start, LocalDateTime end);

    ResourceAllocation getAllocation(Long id);

    ResourceAllocation updateAllocationStatus(Long id, String status);

    List<ResourceAllocation> getAllAllocations();
}
