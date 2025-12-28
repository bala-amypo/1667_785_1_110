package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ResourceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resourceType;
    private String purpose;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String status = "PENDING"; // REQUIRED BY TEST

    @ManyToOne
    @JoinColumn(name = "requested_by") 
    private User requestedBy;
}
