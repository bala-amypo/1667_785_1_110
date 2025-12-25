package com.example.demo.service;

import com.example.demo.entity.Resource;

import java.util.List;

public interface ResourceService {
    Resource createResource(Resource resource);
    List<Resource> getAllResources();
}
