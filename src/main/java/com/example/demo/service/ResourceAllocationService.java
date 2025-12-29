package com.example.demo.service;

import com.example.demo.entity.ResourceAllocation;

import java.util.List;

public interface ResourceAllocationService {
    ResourceAllocation autoAllocate(Long requestId);
    List<ResourceAllocation> getAllAllocations();
    
}
