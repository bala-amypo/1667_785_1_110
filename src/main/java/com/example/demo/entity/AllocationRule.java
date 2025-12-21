package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AllocationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ruleName;  

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RuleType ruleType;

    @Column(nullable = false)
    private Integer priorityWeight;

    @Column(nullable = false)
    private LocalDateTime createdAt;

 
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public enum RuleType {
        FIRST_AVAILABLE,
        PRIORITY_BASED,
        ROUND_ROBIN
    }
}
