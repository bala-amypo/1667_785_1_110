package com.example.demo.service.impl;

import com.example.demo.entity.ResourceAllocation;
import com.example.demo.entity.ResourceRequest;
import com.example.demo.entity.Resource;
import com.example.demo.repository.ResourceAllocationRepository;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.service.ResourceAllocationService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceAllocationServiceImpl implements ResourceAllocationService {

    private final ResourceRequestRepository requestRepo;
    private final ResourceRepository resourceRepo;
    private final ResourceAllocationRepository allocationRepo;

    @Autowired
    public ResourceAllocationServiceImpl(ResourceRequestRepository requestRepo,
                                         ResourceRepository resourceRepo,
                                         ResourceAllocationRepository allocationRepo) {
        this.requestRepo = requestRepo;
        this.resourceRepo = resourceRepo;
        this.allocationRepo = allocationRepo;
    }

@Override
public ResourceAllocation autoAllocate(Long requestId) {
    // directly fetch or return null if not found
    ResourceRequest request = requestRepo.findById(requestId).orElse(null);
    if (request == null) return null;

    List<Resource> available = resourceRepo.findByResourceTypeAndStartTimeBetween(
            request.getResourceType(), request.getStartTime(), request.getEndTime());

    ResourceAllocation allocation = new ResourceAllocation();
    allocation.setRequestedBy(request.getRequestedBy());
    allocation.setResource(available.isEmpty() ? null : available.get(0));
    allocation.setStartTime(request.getStartTime());
    allocation.setEndTime(request.getEndTime());
    allocation.setStatus(available.isEmpty() ? "REJECTED" : "APPROVED");

    return allocationRepo.save(allocation);
}


    @Override
    public ResourceAllocation getAllocation(Long id) {
        return allocationRepo.findById(id).orElse(null);
    }

    @Override
    public List<ResourceAllocation> getAllAllocations() {
        return allocationRepo.findAll();
    }
}
