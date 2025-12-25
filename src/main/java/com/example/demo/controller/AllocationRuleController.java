package com.example.demo.controller;

import com.example.demo.entity.AllocationRule;
import com.example.demo.service.AllocationRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
public class AllocationRuleController {

    private final AllocationRuleService ruleService;

    public AllocationRuleController(AllocationRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<AllocationRule> create(@RequestBody AllocationRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AllocationRule> get(@PathVariable Long id) {
        return ResponseEntity.ok(ruleService.getRule(id));
    }

    @GetMapping
    public ResponseEntity<List<AllocationRule>> getAll() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
}
