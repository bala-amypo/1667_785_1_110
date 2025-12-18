package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.ResourceAllocation;

@Repository
public interface ResourceAllocationRepository extends JpaRepository<ResourceAllocation,Long>{
       
}