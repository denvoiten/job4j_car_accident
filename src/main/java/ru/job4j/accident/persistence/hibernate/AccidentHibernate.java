package ru.job4j.accident.persistence.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;

@Repository
public class AccidentHibernate implements StoreTransaction {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident add(Accident accident) {
        return (Accident) transaction(session -> session.merge(accident), sf);
    }

    public Collection<Accident> getAll() {
        return transaction(session -> session
                .createQuery("select distinct a from Accident a "
                        + "join fetch a.type type "
                        + "join fetch a.rules rules "
                        + "order by a.id", Accident.class)
                .getResultList(), sf);
    }

    public Accident findById(int id) {
        return transaction(session -> session
                .createQuery("select distinct a from Accident a "
                        + "join fetch a.type type "
                        + "join fetch a.rules rules "
                        + "where a.id = :aID", Accident.class)
                .setParameter("aID", id)
                .uniqueResult(), sf);
    }

    public void update(Accident accident) {
        transaction(session -> {
                    session.update(accident);
                    return accident;
                },
                sf);
    }

    public void delete(int id) {
        transaction(session -> session
                .createQuery("delete from Accident a where a.id = :aID")
                .setParameter("aID", id)
                .executeUpdate(), sf);
    }
}
