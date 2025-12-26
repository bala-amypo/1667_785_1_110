package com.example.demo.service;

import com.example.demo.entity.Resource;

import java.util.List;

public interface ResourceService {

    // Create a resource (throws exception if exists/invalid)
    Resource createResource(Resource resource);

    // Get all resources
    List<Resource> getAllResources();

    // Optional helper: get resource by name
    Resource getResourceByName(String resourceName);
}
