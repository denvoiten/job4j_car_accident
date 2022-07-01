package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.persistence.data.RuleRepository;

import java.util.Optional;

@Service
public class RuleServiceData {
    private final RuleRepository ruleRepository;

    public RuleServiceData(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public Optional<Rule> findById(int id) {
        return ruleRepository.findById(id);
    }

    public Iterable<Rule> findAll() {
        return ruleRepository.findAll();
    }
}