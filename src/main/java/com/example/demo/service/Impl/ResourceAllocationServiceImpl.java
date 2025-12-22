package com.example.demo.service;

import com.example.demo.entity.Resource;
import com.example.demo.entity.ResourceAllocation;
import com.example.demo.entity.ResourceRequest;
import com.example.demo.repository.ResourceAllocationRepository;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.repository.ResourceRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceAllocationServiceImpl implements ResourceAllocationService {

    private final ResourceRequestRepository resourceRequestRepository;
    private final ResourceRepository resourceRepository;
    private final ResourceAllocationRepository resourceAllocationRepository;

   
    public ResourceAllocationServiceImpl(ResourceRequestRepository resourceRequestRepository,ResourceRepository resourceRepository,
          ResourceAllocationRepository resourceAllocationRepository) {

        this.resourceRequestRepository = resourceRequestRepository;
        this.resourceRepository = resourceRepository;
        this.resourceAllocationRepository = resourceAllocationRepository;
    }

    @Override
    public ResourceAllocation autoAllocate(Long requestId) {

        ResourceRequest request = resourceRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("ResourceRequest not found"));

       
        Resource resource = resourceRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No resources available"));

        ResourceAllocation allocation = new ResourceAllocation();
        allocation.setRequest(request);
        allocation.setResource(resource);
        allocation.setConflictFlag(false);
        allocation.setNotes("Auto allocated");

        return resourceAllocationRepository.save(allocation);
    }

    @Override
    public ResourceAllocation getAllocation(Long id) {
        return resourceAllocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ResourceAllocation not found"));
    }

    @Override
    public List<ResourceAllocation> getAllAllocations() {
        return resourceAllocationRepository.findAll();
    }
}
