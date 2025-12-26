package com.example.demo.service.impl;

import com.example.demo.entity.Resource;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.service.ResourceService;
import com.example.demo.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepo;

    public ResourceServiceImpl(ResourceRepository resourceRepo) {
        this.resourceRepo = resourceRepo;
    }

    @Override
    public Resource createResource(Resource r) {

        // Basic validation
        if (r.getResourceName() == null || r.getResourceType() == null || r.getCapacity() == null) {
            throw new ValidationException("Invalid resource data");
        }

        // Check if resource already exists
        if (resourceRepo.existsByResourceName(r.getResourceName())) {
            throw new ValidationException("Resource already exists");
        }

        // Save directly
        return resourceRepo.save(r);
    }

    @Override
    public List<Resource> getAllResources() {
        return resourceRepo.findAll();
    }

    @Override
    public Resource getResourceByName(String resourceName) {
        Resource res = resourceRepo.findByResourceName(resourceName);
        if (res == null) {
            throw new ValidationException("Resource not found");
        }
        return res;
    }
}
