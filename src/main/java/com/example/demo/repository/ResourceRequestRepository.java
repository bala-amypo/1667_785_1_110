package com.example.demo.repository;

import com.example.demo.entity.ResourceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ResourceRequestRepository extends JpaRepository<ResourceRequest, Long> {

    List<ResourceRequest> findByRequestedBy_Id(Long id);

    List<ResourceRequest> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
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
package com.example.demo.service;

import com.example.demo.entity.ResourceRequest;

import java.util.List;

public interface ResourceRequestService {

    ResourceRequest createRequest(Long userId, ResourceRequest request);

    List<ResourceRequest> getRequestsByUser(Long userId);

    ResourceRequest updateRequestStatus(Long requestId, String status);
}

package com.example.demo.service.impl;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ResourceRequestService;
import org.springframework.stereotype.Service;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class ResourceRequestServiceImpl implements ResourceRequestService {

    private final ResourceRequestRepository reqRepo;
    private final UserRepository userRepo;

    public ResourceRequestServiceImpl(ResourceRequestRepository reqRepo, UserRepository userRepo) {
        this.reqRepo = reqRepo;
        this.userRepo = userRepo;
    }

    @Override
    public ResourceRequest createRequest(Long userId, ResourceRequest rr) {

       User u = userRepo.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));


        rr.setRequestedBy(u);
        rr.setStatus("PENDING");

        return reqRepo.save(rr);
    }

    @Override
    public List<ResourceRequest> getRequestsByUser(Long userId) {
        return reqRepo.findByRequestedBy_Id(userId);
    }

    @Override
    public ResourceRequest updateRequestStatus(Long id, String status) {

        ResourceRequest r = reqRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        r.setStatus(status);
        return reqRepo.save(r);
    }
}
