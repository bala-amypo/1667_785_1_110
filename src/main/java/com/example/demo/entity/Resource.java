package com.example.demo.entity;

public class Resource{
    private Integer id;
    private String resourceName;
    private String resourceType;
    @Min(1)
    private String capacity;
    private String location;
    private LocalDateTime createdAt;
}