package com.example.demo.service;

import com.example.demo.entity.ResourceRequest;

import java.util.List;

public interface ResourceRequestService {

    // Create a new request for a user
    ResourceRequest createRequest(Long userId, ResourceRequest request);

    // Get all requests for a user
    List<ResourceRequest> getRequestsByUser(Long userId);

    // Update status of a request
    ResourceRequest updateRequestStatus(Long requestId, String status);

    // Optional: get by ID directly
    ResourceRequest getRequestById(Long requestId);
}
