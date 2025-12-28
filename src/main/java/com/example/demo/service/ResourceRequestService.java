package com.example.demo.service;

import com.example.demo.entity.ResourceRequest;

import java.util.List;

public interface ResourceRequestService {

    ResourceRequest createRequest(Long userId, ResourceRequest request);

    List<ResourceRequest> getRequestsByUser(Long userId);

    ResourceRequest updateRequestStatus(Long requestId, String status);

    ResourceRequest getRequestById(Long requestId);
}
