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

    @PostMapping("/{userId}")
    public ResponseEntity<ResourceRequest> createRequest(
            @PathVariable Long userId,
            @RequestBody ResourceRequest request) {

        return ResponseEntity.ok(requestService.createRequest(userId, request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ResourceRequest>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(requestService.getRequestsByUser(userId));
    }

    @PutMapping("/{requestId}/status/{status}")
    public ResponseEntity<ResourceRequest> updateStatus(
            @PathVariable Long requestId,
            @PathVariable String status) {

        return ResponseEntity.ok(
                requestService.updateRequestStatus(requestId, status)
        );
    }
}
