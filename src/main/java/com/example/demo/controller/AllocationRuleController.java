package com.example.demo.controller;

import com.example.demo.entity.AllocationRule;
import com.example.demo.service.AllocationRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AllocationRuleController {

    private final AllocationRuleService allocationRuleService;

    public AllocationRuleController(AllocationRuleService allocationRuleService) {
        this.allocationRuleService = allocationRuleService;
    }

    @PostMapping("/api/rules")
    public AllocationRule createRule(@RequestBody AllocationRule rule) {
        return allocationRuleService.createRule(rule);
    }

    @GetMapping("/api/rules")
    public List<AllocationRule> getAllRules() {
        return allocationRuleService.getAllRules();
    }

  
    @GetMapping("/api/rules/{id}")
    public AllocationRule getRule(@PathVariable Long id) {
        return allocationRuleService.getRule(id);
    }
}
