package com.example.demo.entity;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AllocationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ruleName;

    private String ruleType;
    private Integer priorityWeight;

@Column(name = "created_at", nullable = false, updatable = false)
private LocalDateTime createdAt;

@PrePersist
public void onCreate() {
    this.createdAt = LocalDateTime.now();
}

}
