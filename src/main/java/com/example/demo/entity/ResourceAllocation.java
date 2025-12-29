package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ResourceAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Resource resource;

    @ManyToOne
    private ResourceRequest request;

    private LocalDateTime allocatedAt = LocalDateTime.now(); 

    private String notes;

    private boolean conflict = false;

}
