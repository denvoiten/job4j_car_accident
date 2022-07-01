package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.persistence.hibernate.TypeHibernate;

import java.util.Collection;

@Service
public class AccidentTypeService {
    private final TypeHibernate typeHibernate;

    public AccidentTypeService(TypeHibernate typeHibernate) {
        this.typeHibernate = typeHibernate;
    }

    public AccidentType findById(int id) {
        return typeHibernate.findById(id);
    }

    public Collection<AccidentType> getTypes() {
        return typeHibernate.getTypes();
    }
}
