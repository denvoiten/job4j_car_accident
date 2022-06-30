package ru.job4j.accident.persistence;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {
    private static final Map<Integer, Accident> ACCIDENTS = new HashMap<>();

    static {
        ACCIDENTS.put(1, Accident.of(1, "Accident 1", "Bla", "City A, Street A"));
        ACCIDENTS.put(2, Accident.of(2, "Accident 2", "Bla bla", "City B, Street B"));
        ACCIDENTS.put(3, Accident.of(3, "Accident 3", "Bla bla bla", "City C, Street C"));
    }

    public Collection<Accident> getAll() {
        return ACCIDENTS.values();
    }
}
