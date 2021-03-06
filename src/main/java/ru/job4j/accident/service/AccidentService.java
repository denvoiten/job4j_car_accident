package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.persistence.data.AccidentRepository;

import java.util.Optional;

@Service
public class AccidentService {
    private final AccidentRepository accidentRepository;

    public AccidentService(AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    public Iterable<Accident> getAll() {
        return accidentRepository.findAll();
    }

    public void add(Accident accident) {
        accidentRepository.save(accident);
    }

    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    public void delete(Accident accident) {
        accidentRepository.delete(accident);
    }
}
