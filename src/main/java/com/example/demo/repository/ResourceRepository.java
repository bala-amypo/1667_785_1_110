package com.example.demo.repository;

import com.example.demo.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    boolean existsByResourceName(String resourceName);

    List<Resource> findByResourceType(String resourceType);

    Optional<Resource> findById(Long id); // For allocations
    Optional<Resource> findByResourceName(String resourceName);
    

}
