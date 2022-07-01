package ru.job4j.accident.persistence.mem;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentTypeMem {
    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();

    public AccidentTypeMem() {
        AtomicInteger atomicId = new AtomicInteger();
        types.computeIfAbsent(atomicId.incrementAndGet(), id -> AccidentType.of(id, "Две машины"));
        types.computeIfAbsent(atomicId.incrementAndGet(), id -> AccidentType.of(id, "Машина и человек"));
        types.computeIfAbsent(atomicId.incrementAndGet(), id -> AccidentType.of(id, "Машина и велосипед"));
    }

    public AccidentType findById(int id) {
        return types.get(id);
    }

    public Collection<AccidentType> getTypes() {
        return types.values();
    }
}
