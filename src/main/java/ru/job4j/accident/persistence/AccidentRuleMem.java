package ru.job4j.accident.persistence;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentRuleMem {

    private final List<Rule> rules = Collections.synchronizedList(new ArrayList<>());

    public AccidentRuleMem() {
        AtomicInteger atomicId = new AtomicInteger();
        rules.add(Rule.of(atomicId.getAndIncrement(), "Статья. 1"));
        rules.add(Rule.of(atomicId.getAndIncrement(), "Статья. 2"));
        rules.add(Rule.of(atomicId.getAndIncrement(), "Статья. 3"));
    }

    public Rule findById(int id) {
        return rules.get(id);
    }

    public Collection<Rule> getRules() {
        return rules;
    }

    public Set<Rule> getRulesAccident(Set<Integer> rulesID) {
        Set<Rule> accidentRules = new TreeSet<>();
        for (Integer id : rulesID) {
            accidentRules.add(findById(id));
        }
        return accidentRules;
    }
}
