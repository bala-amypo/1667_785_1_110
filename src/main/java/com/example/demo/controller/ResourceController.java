package com.example.demo.controller;

import com.example.demo.entity.Resource;
import com.example.demo.service.ResourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResourceController {

    @PostMapping("/api/resources")
    public Resource create(@RequestBody Resource resource) {
        return resourceService.createResource(resource);
    }

    @GetMapping("/api/resources")
    public List<Resource> getAll() {
        return resourceService.getAllResources();
    }

    @GetMapping("/api/resources/{id}")
    public Resource getById(@PathVariable Long id) {
        return resourceService.getResource(id);
    }
}
