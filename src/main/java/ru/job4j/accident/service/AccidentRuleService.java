package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.persistence.hibernate.RuleHibernate;

import java.util.Collection;

@Service
public class AccidentRuleService {
    private final RuleHibernate ruleHibernate;

    public AccidentRuleService(RuleHibernate ruleHibernate) {
        this.ruleHibernate = ruleHibernate;
    }

    public Rule findById(int id) {
        return ruleHibernate.findById(id);
    }

    public Collection<Rule> getRules() {
        return ruleHibernate.getRules();
    }
}
