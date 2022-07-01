package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.persistence.hibernate.AccidentHibernate;

import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentHibernate accidentHibernate;

    public AccidentService(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }

    public Collection<Accident> getAll() {
        return accidentHibernate.getAll();
    }

    public void add(Accident accident) {
        accidentHibernate.add(accident);
    }

    public Accident findById(int id) {
        return accidentHibernate.findById(id);
    }

    public void update(Accident accident) {
        accidentHibernate.update(accident);
    }

    public void delete(int id) {
        accidentHibernate.delete(id);
    }
}
