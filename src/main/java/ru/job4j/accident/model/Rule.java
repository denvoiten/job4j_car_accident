package ru.job4j.accident.model;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Rule implements Comparable<Rule> {
    private int id;
    private String name;

    public static Rule of(int id, String name) {
        Rule rule = new Rule();
        rule.id = id;
        rule.name = name;
        return rule;
    }

    @Override
    public int compareTo(Rule rule) {
        return Integer.compare(this.id, rule.getId());
    }
}
