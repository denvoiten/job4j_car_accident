package ru.job4j.accident.persistence.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;

@Repository
public class TypeHibernate implements StoreTransaction {
    private final SessionFactory sf;

    public TypeHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public AccidentType findById(int id) {
        return transaction(session -> session
                        .get(AccidentType.class, id),
                sf);
    }

    public Collection<AccidentType> getTypes() {
        return transaction(session -> session
                .createQuery("from AccidentType at order by at.id")
                .getResultList(), sf);
    }
}
