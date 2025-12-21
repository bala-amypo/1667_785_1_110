package com.example.demo.service;

import com.example.demo.entity.ResourceAllocation;
import java.time.LocalDateTime;
import java.util.List;

public interface ResourceAllocationService {

    ResourceAllocation createAllocation(ResourceAllocation allocation); // only allocation

    ResourceAllocation getAllocation(Long allocationId); // use same name as controller calls or adjust

    List<ResourceAllocation> getAllocationsByTypeAndTime(String resourceType, LocalDateTime start, LocalDateTime end);

    List<ResourceAllocation> getAllocationsByUser(Long userId);

    ResourceAllocation updateAllocationStatus(Long allocationId, String status);
}
