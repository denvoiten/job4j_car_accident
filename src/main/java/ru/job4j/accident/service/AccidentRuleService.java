package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.persistence.AccidentRuleMem;

import java.util.Collection;
import java.util.Set;

@Service
public class AccidentRuleService {
    private final AccidentRuleMem accidentRuleMem;

    public AccidentRuleService(AccidentRuleMem accidentRuleMem) {
        this.accidentRuleMem = accidentRuleMem;
    }

    public Rule findById(int id) {
        return accidentRuleMem.findById(id);
    }

    public Collection<Rule> getRules() {
        return accidentRuleMem.getRules();
    }

    public Set<Rule> getRulesAccident(Set<Integer> rulesID) {
        return accidentRuleMem.getRulesAccident(rulesID);
    }
}
