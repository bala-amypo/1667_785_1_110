package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.AllocationRule;

@Repository
public interface AllocationRulerepository extends JpaRepository<Resource,Integer>{
       
}