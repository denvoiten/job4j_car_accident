package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.persistence.jdbc.AccidentJdbcTemplate;

import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentJdbcTemplate accidentJdbcTemplate;

    public AccidentService(AccidentJdbcTemplate accidentJdbcTemplate) {
        this.accidentJdbcTemplate = accidentJdbcTemplate;
    }

    public Collection<Accident> getAll() {
        return accidentJdbcTemplate.getAll();
    }

    public void add(Accident accident) {
        accidentJdbcTemplate.add(accident);
    }

    public Accident findById(int id) {
        return accidentJdbcTemplate.findById(id);
    }

    public void update(Accident accident) {
        accidentJdbcTemplate.update(accident);
    }

    public void delete(int id) {
        accidentJdbcTemplate.delete(id);
    }
}
