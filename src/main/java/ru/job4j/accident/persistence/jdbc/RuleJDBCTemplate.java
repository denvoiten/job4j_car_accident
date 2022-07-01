package ru.job4j.accident.persistence.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

public class RuleJDBCTemplate {
    private final JdbcTemplate jdbc;

    public RuleJDBCTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Rule findById(int id) {
        return jdbc.queryForObject("select id, rule_name from accident_rule where id = ?",
                (rs, line) -> Rule.of(
                        rs.getInt("id"),
                        rs.getString("rule_name")),
                id);
    }

    public Collection<Rule> getRules() {
        return jdbc.query("select id, rule_name from accident_rule order by id",
                (rs, line) -> Rule.of(
                        rs.getInt("id"),
                        rs.getString("rule_name"))
        );
    }
}
