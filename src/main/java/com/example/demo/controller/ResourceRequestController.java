package com.example.demo.controller;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.service.ResourceRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class ResourceRequestController {

    private final ResourceRequestService requestService;

    public ResourceRequestController(ResourceRequestService requestService) {
        this.requestService = requestService;
    }

    // Create a new request for a user
    @PostMapping("/{userId}")
    public ResourceRequest createRequest(
            @PathVariable Long userId,
            @RequestBody ResourceRequest request) {

        return requestService.createRequest(userId, request);
    }

    // Get all requests for a specific user
    @GetMapping("/user/{userId}")
    public List<ResourceRequest> getByUser(@PathVariable Long userId) {
        return requestService.getRequestsByUser(userId);
    }

    // Update the status of a request
    @PutMapping("/{requestId}/status/{status}")
    public ResourceRequest updateStatus(
            @PathVariable Long requestId,
            @PathVariable String status) {

        return requestService.updateRequestStatus(requestId, status);
    }

    // Optional: Get request by ID
    @GetMapping("/{requestId}")
    public ResourceRequest getRequest(@PathVariable Long requestId) {
        return requestService.getRequestById(requestId);
    }
}
