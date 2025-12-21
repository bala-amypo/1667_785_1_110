package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class ResourceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resourceType;
    @ManyToOne
    @JoinColumn(name = "requested_by", nullable = false)
    private User requestedBy;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Column(nullable = false)
    private String purpose;

    private String status;

    @PrePersist
    public void prePersist() {
        if (this.status == null || this.status.isEmpty()) {
            this.status = "PENDING";
        }
    }
}
