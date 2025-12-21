package com.example.demo.controller;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.service.ResourceRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResourceRequestController {

    private final ResourceRequestService resourceRequestService;

    public ResourceRequestController(ResourceRequestService resourceRequestService) {
        this.resourceRequestService = resourceRequestService;
    }

    @PostMapping("/api/requests/{userId}")
    public ResourceRequest createRequest(
            @PathVariable Long userId,
            @RequestBody ResourceRequest request) {
        return resourceRequestService.createRequest(userId, request);
    }

    @GetMapping("/api/requests/user/{userId}")
    public List<ResourceRequest> getRequestsByUser(@PathVariable Long userId) {
        return resourceRequestService.getRequestsByUser(userId);
    }

    @GetMapping("/api/requests/{id}")
    public ResourceRequest getRequest(@PathVariable Long id) {
        return resourceRequestService.getRequest(id);
    }

    @PutMapping("/api/requests/status/{requestId}")
    public ResourceRequest updateRequestStatus(
            @PathVariable Long requestId,
            @RequestParam String status) {
        return resourceRequestService.updateRequestStatus(requestId, status);
    }
}
