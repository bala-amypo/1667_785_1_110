package com.example.demo.controller;

import com.example.demo.entity.ResourceAllocation;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResourceAllocationController {

    private final ResourceAllocationService resourceAllocationService;
    
    public ResourceAllocationController(ResourceAllocationService resourceAllocationService) {
        this.resourceAllocationService = resourceAllocationService;
    }

    @PostMapping("/api/allocations/auto/{requestId}")
    public ResourceAllocation autoAllocate(@PathVariable Long requestId) {
        return resourceAllocationService.autoAllocate(requestId);
    }

    @GetMapping("/api/allocations")
    public List<ResourceAllocation> getAllAllocations() {
        return resourceAllocationService.getAllAllocations();
    }

    @GetMapping("/api/allocations/{id}")
    public ResourceAllocation getAllocation(@PathVariable Long id) {
        return resourceAllocationService.getAllocation(id);
    }
}
