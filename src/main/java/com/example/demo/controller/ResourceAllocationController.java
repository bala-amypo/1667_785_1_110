package com.example.demo.controller;

import com.example.demo.entity.ResourceAllocation;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/allocations")
public class ResourceAllocationController {

    private final ResourceAllocationService resourceAllocationService;

    @Autowired
    public ResourceAllocationController(ResourceAllocationService resourceAllocationService) {
        this.resourceAllocationService = resourceAllocationService;
    }

    @PostMapping("/{resourceId}")
    public ResourceAllocation createAllocation(@PathVariable Long resourceId,@RequestBody ResourceAllocation allocation) {
        return resourceAllocationService.createAllocation(resourceId, allocation);
    }

    @GetMapping("/{allocationId}")
    public ResourceAllocation getAllocationById(@PathVariable Long allocationId) {
        return resourceAllocationService.getAllocationById(allocationId);
    }

    @GetMapping("/resource/{resourceType}/{startTime}/{endTime}")
    public List<ResourceAllocation> getAllocationsByTypeAndTime( @PathVariable String resourceType, @PathVariable String startTime, @PathVariable String endTime) {

        LocalDateTime start = LocalDateTime.parse(startTime);
        LocalDateTime end = LocalDateTime.parse(endTime);

        return resourceAllocationService.getAllocationsByTypeAndTime(resourceType, start, end);
    }

    @GetMapping("/user/{userId}")
    public List<ResourceAllocation> getAllocationsByUser(@PathVariable Long userId) {
        return resourceAllocationService.getAllocationsByUser(userId);
    }

    @PutMapping("/status/{allocationId}/{status}")
    public ResourceAllocation updateAllocationStatus( @PathVariable Long allocationId, @PathVariable String status) {
        return resourceAllocationService.updateAllocationStatus(allocationId, status);
    }
}
