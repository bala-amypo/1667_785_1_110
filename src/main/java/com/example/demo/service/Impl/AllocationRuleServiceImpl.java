package com.example.demo.service.impl;

import com.example.demo.entity.AllocationRule;
import com.example.demo.repository.AllocationRuleRepository;
import com.example.demo.service.AllocationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationRuleServiceImpl implements AllocationRuleService {

    private final AllocationRuleRepository ruleRepo;

    public AllocationRuleServiceImpl(AllocationRuleRepository ruleRepo) {
        this.ruleRepo = ruleRepo;
    }

    @Override
    public AllocationRule createRule(AllocationRule rule) {
        if (ruleRepo.existsByRuleName(rule.getRuleName())) {
            throw new RuntimeException("Rule already exists");
        }
        return ruleRepo.save(rule);
    }

    @Override
    public AllocationRule getRule(Long id) {
        AllocationRule rule = ruleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
        return rule;
    }

    @Override
    public List<AllocationRule> getAllRules() {
        return ruleRepo.findAll();
    }
}
