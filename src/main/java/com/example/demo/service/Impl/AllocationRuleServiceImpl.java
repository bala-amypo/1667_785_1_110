package com.example.demo.service;

import com.example.demo.entity.AllocationRule;
import com.example.demo.repository.AllocationRuleRepository;
import org.springframework.stereotype.Service;
import com.example.demo.exception.ValidationException;

import java.util.List;

@Service
public class AllocationRuleServiceImpl implements AllocationRuleService {

    private final AllocationRuleRepository allocationRuleRepository;

    public AllocationRuleServiceImpl(AllocationRuleRepository allocationRuleRepository) {
        this.allocationRuleRepository = allocationRuleRepository;
    }

    @Override
    public AllocationRule createRule(AllocationRule rule) {

        if (allocationRuleRepository.existsByRuleName(rule.getRuleName())) {
            throw new ValidationException("AllocationRule with this ruleName already exists");
         }


        if (rule.getPriorityWeight() < 0) {
            throw new RuntimeException("priorityWeight must be greater than or equal to 0");
        }

        return allocationRuleRepository.save(rule);
    }

    @Override
    public AllocationRule getRule(Long id) {
        return allocationRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AllocationRule not found"));
    }

    @Override
    public List<AllocationRule> getAllRules() {
        return allocationRuleRepository.findAll();
    }
}
