package ru.job4j.accident.persistence.mem;

import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AccidentRuleMem {

    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    public AccidentRuleMem() {
        AtomicInteger atomicId = new AtomicInteger();
        rules.put(1, Rule.of(atomicId.getAndIncrement(), "Статья. 1"));
        rules.put(2, Rule.of(atomicId.getAndIncrement(), "Статья. 2"));
        rules.put(3, Rule.of(atomicId.getAndIncrement(), "Статья. 3"));
    }

    public Rule findById(int id) {
        return rules.get(id);
    }

    public Collection<Rule> getRules() {
        return rules.values();
    }

    public Set<Rule> getRulesAccident(Set<Integer> rulesID) {
        Set<Rule> accidentRules = new TreeSet<>();
        for (Integer id : rulesID) {
            accidentRules.add(findById(id));
        }
        return accidentRules;
    }
}
