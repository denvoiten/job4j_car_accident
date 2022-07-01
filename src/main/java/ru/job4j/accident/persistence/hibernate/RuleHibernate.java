package ru.job4j.accident.persistence.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

@Repository
public class RuleHibernate implements StoreTransaction {
    private final SessionFactory sf;

    public RuleHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Rule findById(int id) {
        return transaction(session -> session
                        .get(Rule.class, id),
                sf);
    }

    public Collection<Rule> getRules() {
        return transaction(session -> session
                .createQuery("from Rule r order by r.id")
                .getResultList(), sf);
    }

}
