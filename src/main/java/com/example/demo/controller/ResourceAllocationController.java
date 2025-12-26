package com.example.demo.controller;

import com.example.demo.entity.ResourceAllocation;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/allocations")
public class ResourceAllocationController {

    private final ResourceAllocationService allocationService;

    public ResourceAllocationController(ResourceAllocationService allocationService) {
        this.allocationService = allocationService;
    }

    @PostMapping("/auto/{requestId}")
    public ResponseEntity<ResourceAllocation> autoAllocate(@PathVariable Long requestId) {
        return ResponseEntity.ok(allocationService.autoAllocate(requestId));
    }

    @GetMapping
    public ResponseEntity<List<ResourceAllocation>> getAll() {
        return ResponseEntity.ok(allocationService.getAllAllocations());
    }
}
