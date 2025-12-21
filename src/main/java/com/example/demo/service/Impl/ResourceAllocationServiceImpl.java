package com.example.demo.service.impl;

import com.example.demo.entity.ResourceAllocation;
import com.example.demo.repository.ResourceAllocationRepository;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResourceAllocationServiceImpl implements ResourceAllocationService {

    private final ResourceAllocationRepository allocationRepository;

    @Autowired
    public ResourceAllocationServiceImpl(ResourceAllocationRepository allocationRepository) {
        this.allocationRepository = allocationRepository;
    }

    @Override
    public ResourceAllocation createAllocation(ResourceAllocation allocation) {
        return allocationRepository.save(allocation);
    }

    @Override
    public ResourceAllocation getAllocation(Long id) {
        ResourceAllocation allocation = allocationRepository.findById(id).orElse(null);
        return allocation; 
    }

    @Override
    public List<ResourceAllocation> getAllocationsByResourceTypeAndTime(String resourceType, LocalDateTime start, LocalDateTime end) {
        return allocationRepository.findByResourceTypeAndStartTimeBetween(resourceType, start, end);
    }

    @Override
    public ResourceAllocation updateAllocationStatus(Long id, String status) {
        ResourceAllocation allocation = allocationRepository.findById(id).orElse(null);
        if (allocation != null) {
            allocation.setStatus(status);
            return allocationRepository.save(allocation);
        }
        return null;
    }
}
