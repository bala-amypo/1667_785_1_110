package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource,Integer>{
       
}