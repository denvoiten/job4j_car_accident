package ru.job4j.accident.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Accident {
    private int id;
    private String name;
    private String text;
    private String address;

    private AccidentType type;
    private Set<Rule> rules;

    public static Accident of(int id, String name, String text, String address, AccidentType type, Set<Rule> rules) {
        Accident accident = new Accident();
        accident.id = id;
        accident.name = name;
        accident.text = text;
        accident.address = address;
        accident.type = type;
        accident.rules = rules;
        return accident;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
