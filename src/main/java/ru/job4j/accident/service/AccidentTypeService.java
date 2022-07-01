package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.persistence.jdbc.TypeJDBCTemplate;

import java.util.Collection;

@Service
public class AccidentTypeService {
    private final TypeJDBCTemplate typeJDBCTemplate;

    public AccidentTypeService(TypeJDBCTemplate typeJDBCTemplate) {
        this.typeJDBCTemplate = typeJDBCTemplate;
    }

    public AccidentType findById(int id) {
        return typeJDBCTemplate.findById(id);
    }

    public Collection<AccidentType> getTypes() {
        return typeJDBCTemplate.getTypes();
    }
}
