package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.ResourceRequest;

@Repository
public interface ResourceRequestRepository extends JpaRepository<ResourceRequest,Long>{
       
}