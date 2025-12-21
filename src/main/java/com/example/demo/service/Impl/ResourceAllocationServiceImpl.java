package com.example.demo.service.Impl;

import com.example.demo.entity.ResourceAllocation;
import com.example.demo.repository.ResourceAllocationRepository;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ResourceAllocation getAllocation(Long allocationId) {
        return allocationRepository.findById(allocationId).orElse(null);
    }

    @Override
    public List<ResourceAllocation> getAllocationsByTypeAndTime(String resourceType, LocalDateTime start, LocalDateTime end) {
        return allocationRepository.findByResourceTypeAndStartTimeBetween(resourceType, start, end);
    }

    @Override
    public List<ResourceAllocation> getAllocationsByUser(Long userId) {
        return allocationRepository.findByRequestedBy_Id(userId);
    }

    @Override
    public ResourceAllocation updateAllocationStatus(Long allocationId, String status) {
        ResourceAllocation allocation = allocationRepository.findById(allocationId).orElse(null);
        if (allocation != null) {
            allocation.setStatus(status);
            allocationRepository.save(allocation);
        }
        return allocation;
    }
}
