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
        // Simple check if user exists
        User u = userRepo.findById(userId); // <-- custom repo method returning User directly
        if (u == null) {
            throw new ResourceNotFoundException("User not found");
        }

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
        ResourceRequest r = reqRepo.findById(id); // <-- custom repo method returning ResourceRequest directly
        if (r == null) {
            throw new ResourceNotFoundException("Request not found");
        }
        r.setStatus(status);
        return reqRepo.save(r);
    }
}
