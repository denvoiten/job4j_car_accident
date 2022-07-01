package ru.job4j.accident.persistence.mem;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();

    private final AtomicInteger id = new AtomicInteger();

    public Collection<Accident> getAll() {
        return accidents.values();
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public void add(Accident accident) {
        accident.setId(id.incrementAndGet());
        accidents.putIfAbsent(accident.getId(), accident);
    }

    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }
}
