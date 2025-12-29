package com.example.demo.repository;

import com.example.demo.entity.ResourceAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResourceAllocationRepository extends JpaRepository<ResourceAllocation, Long> {

    Optional<ResourceAllocation> findById(Long id); 
    ResourceAllocation findTopByResourceOrderByAllocatedAtDesc(Resource resource);
}
