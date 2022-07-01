package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.persistence.jdbc.RuleJDBCTemplate;

import java.util.Collection;

@Service
public class AccidentRuleService {
    private final RuleJDBCTemplate ruleJDBCTemplate;

    public AccidentRuleService(RuleJDBCTemplate ruleJDBCTemplate) {
        this.ruleJDBCTemplate = ruleJDBCTemplate;
    }

    public Rule findById(int id) {
        return ruleJDBCTemplate.findById(id);
    }

    public Collection<Rule> getRules() {
        return ruleJDBCTemplate.getRules();
    }
}
