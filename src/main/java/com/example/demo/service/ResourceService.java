package com.example.demo.service;

import com.example.demo.entity.Resource;
import com.example.demo.entity.ResourceAllocation;

import java.util.List;

public interface ResourceService {


    Resource createResource(Resource resource);

    List<Resource> getAllResources();

    Resource getResourceByName(String resourceName);

    ResourceAllocation findTopByResourceOrderByAllocatedAtDesc(Resource resource);
}
