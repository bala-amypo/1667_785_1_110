package com.example.demo.controller;

import com.example.demo.entity.ResourceAllocation;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocations")
public class ResourceAllocationController {

    @Autowired
    private ResourceAllocationService resourceAllocationService;

    @PostMapping("/auto/{requestId}")
    public ResourceAllocation autoAllocate(@PathVariable Long requestId) {
        return resourceAllocationService.autoAllocate(requestId);
    }

    @GetMapping("/")
    public List<ResourceAllocation> getAllAllocations() {
        return resourceAllocationService.getAllAllocations();
    }

    @GetMapping("/{id}")
    public ResourceAllocation getAllocation(@PathVariable Long id) {
        return resourceAllocationService.getAllocation(id);
    }
}
