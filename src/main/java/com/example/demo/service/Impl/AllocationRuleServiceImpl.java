package com.example.demo.service.impl;

import com.example.demo.entity.AllocationRule;
import com.example.demo.exception.ValidationException;
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
            throw new ValidationException("Rule exists");
        }
        return ruleRepo.save(rule);
    }

    @Override
    public AllocationRule getRule(Long id) {
        return ruleRepo.findById(id)
                .orElseThrow(() -> new ValidationException("Rule not found"));
    }

    @Override
    public List<AllocationRule> getAllRules() {
        return ruleRepo.findAll();
    }
}
