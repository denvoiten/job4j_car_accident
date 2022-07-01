package ru.job4j.accident.persistence.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;

public class TypeJDBCTemplate {
    private final JdbcTemplate jdbc;

    public TypeJDBCTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public AccidentType findById(int id) {
        return jdbc.queryForObject("select id, type_name from accident_type where id = ?",
                (rs, line) -> AccidentType.of(
                        rs.getInt("id"),
                        rs.getString("type_name")),
                id);

    }

    public Collection<AccidentType> getTypes() {
        return jdbc.query("select id, type_name from accident_type",
                (rs, line) -> AccidentType.of(
                        rs.getInt("id"),
                        rs.getString("type_name"))
        );
    }
}
