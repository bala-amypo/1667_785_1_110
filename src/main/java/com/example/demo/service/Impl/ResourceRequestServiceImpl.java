package com.example.demo.service.impl;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ResourceRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResourceRequestServiceImpl implements ResourceRequestService {

    private final ResourceRequestRepository resourceRequestRepository;
    private final UserRepository userRepository;

    public ResourceRequestServiceImpl(ResourceRequestRepository resourceRequestRepository, UserRepository userRepository) {
        this.resourceRequestRepository = resourceRequestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResourceRequest createRequest(Long userId, ResourceRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not exists"));

        if (request.getPurpose() == null || request.getPurpose().isEmpty()) {
            throw new RuntimeException("Purpose is required");
        }
        LocalDateTime start = request.getStartTime();
        LocalDateTime end = request.getEndTime();

        if (start == null || end == null || !start.isBefore(end)) {
            throw new RuntimeException("Start time must be before end time");
        }

        request.setRequestedBy(user);
        request.setStatus("PENDING");

        return resourceRequestRepository.save(request);
    }

    @Override
    public List<ResourceRequest> getRequestsByUser(Long userId) {
        return resourceRequestRepository.findByRequestedBy_Id(userId);
    }

    @Override
    public ResourceRequest getRequest(Long id) {
        return resourceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ResourceRequest not exists"));
    }

    @Override
    public ResourceRequest updateRequestStatus(Long requestId, String status) {

        ResourceRequest request = resourceRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("ResourceRequest not exists"));

        
        if (!status.equals("PENDING") &&
            !status.equals("APPROVED") &&
            !status.equals("REJECTED")) {
            throw new RuntimeException("Invalid status");
        }

        request.setStatus(status);
        return resourceRequestRepository.save(request);
    }
}
