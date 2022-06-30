package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.persistence.AccidentTypeMem;

import java.util.Collection;

@Service
public class AccidentTypeService {
    private final AccidentTypeMem accidentTypeMem;

    public AccidentTypeService(AccidentTypeMem accidentTypeMem) {
        this.accidentTypeMem = accidentTypeMem;
    }

    public AccidentType findById(int id) {
        return accidentTypeMem.findById(id);
    }

    public Collection<AccidentType> getTypes() {
        return accidentTypeMem.getTypes();
    }
}
