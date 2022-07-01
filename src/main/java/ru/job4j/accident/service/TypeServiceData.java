package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.persistence.data.TypeRepository;

import java.util.Optional;

@Service
public class TypeServiceData {
    private final TypeRepository typeRepository;

    public TypeServiceData(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public Optional<AccidentType> findById(int id) {
        return typeRepository.findById(id);
    }

    public Iterable<AccidentType> findAll() {
        return typeRepository.findAll();
    }
}
