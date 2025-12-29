package com.example.demo.service.impl;

import com.example.demo.entity.Resource;
import com.example.demo.entity.ResourceAllocation;
import com.example.demo.entity.ResourceRequest;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.ResourceAllocationRepository;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new ValidationException("Request not found"));

        List<Resource> resources = resourceRepo.findByResourceType(rr.getResourceType());
        if (resources.isEmpty()) {
            throw new ValidationException("No resource available");
        }
        if (lastAlloc != null) {

    // if time over, release resource
    if (lastAlloc.isConflict() &&
        rr.getEndTime().isBefore(java.time.LocalDateTime.now())) {

        lastAlloc.setConflict(false);
        allocRepo.save(lastAlloc);
    }

    // block if still in use
    if (lastAlloc.isConflict()) {
        throw new ValidationException("Resource is already in use");
    }

        ResourceAllocation alloc = new ResourceAllocation();
        alloc.setRequest(rr);
        alloc.setResource(resources.get(0));
        
        alloc.setConflict(true);

        return allocRepo.save(alloc);
    }

    @Override
    public List<ResourceAllocation> getAllAllocations() {
        return allocRepo.findAll();
    }
}
