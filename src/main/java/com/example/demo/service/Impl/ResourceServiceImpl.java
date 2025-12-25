package com.example.demo.service.impl;

import com.example.demo.entity.Resource;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.service.ResourceService;
import org.springframework.stereotype.Service;
import com.example.demo.exception.ValidationException;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepo;

    public ResourceServiceImpl(ResourceRepository resourceRepo) {
        this.resourceRepo = resourceRepo;
    }

    @Override
    public Resource createResource(Resource r) {

        if (r.getResourceName() == null || r.getResourceType() == null || r.getCapacity() == null) {
            throw new RuntimeException("Invalid resource");
        }

        
        if (resourceRepo.existsByResourceName(r.getResourceName())) {
             throw new ValidationException("Resource exists");
        }


        return resourceRepo.save(r);
    }

    @Override
    public List<Resource> getAllResources() {
        return resourceRepo.findAll();
    }
}
