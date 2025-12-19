package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class ResourceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resourceType;
    private User requestedBy;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @NotNull
    private String purpose;
    private String status;
    private ResourceAllocation resourceAllocation;

    public ResourceRequest(){

    }

    public ResourceRequest( ){
        this.id = id;
        this.resourceType = resourceType;
        this.startTime = startTime;
        this.requestedBy = requestedBy;
        this.startTime = startTime;
        
    }


}
