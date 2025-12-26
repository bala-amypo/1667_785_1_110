package com.example.demo.repository;

import com.example.demo.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    // Check if resource exists by name
    boolean existsByResourceName(String resourceName);

    // Find resources by type
    List<Resource> findByResourceType(String resourceType);

    // Direct find by name (returns null if not found)
    Resource findByResourceName(String resourceName);
}
