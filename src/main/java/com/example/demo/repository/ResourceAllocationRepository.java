package com.example.demo.repository;

import com.example.demo.entity.ResourceAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceAllocationRepository extends JpaRepository<ResourceAllocation, Long> {
}
package com.example.demo.controller;

import com.example.demo.entity.ResourceAllocation;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
package com.example.demo.service;

import com.example.demo.entity.ResourceAllocation;

import java.util.List;

public interface ResourceAllocationService {

    ResourceAllocation autoAllocate(Long requestId);

    List<ResourceAllocation> getAllAllocations();
}
package com.example.demo.service.impl;

import com.example.demo.entity.Resource;
import com.example.demo.entity.ResourceAllocation;
import com.example.demo.entity.ResourceRequest;
import com.example.demo.repository.ResourceAllocationRepository;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.stereotype.Service;
import com.example.demo.exception.ValidationException;

import java.util.List;

@Service
public class ResourceAllocationServiceImpl implements ResourceAllocationService {

    private final ResourceRequestRepository reqRepo;
    private final ResourceRepository resourceRepo;
    private final ResourceAllocationRepository allocRepo;

    public ResourceAllocationServiceImpl(ResourceRequestRepository reqRepo,
                                         ResourceRepository resourceRepo,
                                         ResourceAllocationRepository allocRepo) {
        this.reqRepo = reqRepo;
        this.resourceRepo = resourceRepo;
        this.allocRepo = allocRepo;
    }

    @Override
    public ResourceAllocation autoAllocate(Long requestId) {

        ResourceRequest rr = reqRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        List<Resource> resources = resourceRepo.findByResourceType(rr.getResourceType());

       if (resources.isEmpty()) {
          throw new ValidationException("No resource available");
         }


        ResourceAllocation alloc = new ResourceAllocation();
        alloc.setRequest(rr);
        alloc.setResource(resources.get(0));

        return allocRepo.save(alloc);
    }

    @Override
    public List<ResourceAllocation> getAllAllocations() {
        return allocRepo.findAll();
    }
}

