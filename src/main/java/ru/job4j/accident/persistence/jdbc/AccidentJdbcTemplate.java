package ru.job4j.accident.persistence.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.Set;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Collection<Accident> getAll() {
        return jdbc.query("select ac.id, ac.name, ac.text, ac.address, "
                        + "type_id, at.type_name from accident as ac "
                        + "join accident_type as at on ac.type_id = at.id order by ac.id",
                (rs, line) -> Accident.of(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("text"),
                        rs.getString("address"),
                        AccidentType.of(rs.getInt("type_id"), rs.getString("type_name")),
                        Set.copyOf(getRuleByAccidentId(rs.getInt("id")))));
    }

    public Accident findById(int id) {
        return jdbc.queryForObject("select ac.id, ac.name, ac.text, ac.address, "
                        + "type_id, at.type_name from accident as ac "
                        + "join accident_type as at on ac.type_id = at.id "
                        + "where ac.id = ?",
                (rs, line) -> Accident.of(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("text"),
                        rs.getString("address"),
                        AccidentType.of(rs.getInt("type_id"), rs.getString("type_name")),
                        Set.copyOf(getRuleByAccidentId(rs.getInt("id")))),
                id);
    }

    private Collection<Rule> getRuleByAccidentId(int id) {
        return jdbc.query("select r.id, r.rule_name from accident_rules ar "
                        + "join accident_rule r on ar.rule_id = r.id where accident_id = ? order by r.id",
                (rs, line) -> Rule.of(rs.getInt("id"),
                        rs.getString("rule_name")), id
        );
    }

    public Accident add(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "insert into accident (name, text, address, type_id) values (?, ?, ?, ?)",
                            new String[]{"id"});
                    ps.setString(1, accident.getName());
                    ps.setString(2, accident.getText());
                    ps.setString(3, accident.getAddress());
                    ps.setInt(4, accident.getType().getId());
                    return ps;
                }, keyHolder
        );
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_rules(accident_id, rule_id) values(?, ?)",
                    keyHolder.getKey(),
                    rule.getId());
        }
        return accident;
    }

    public void update(Accident accident) {
        jdbc.update("delete from accident_rules where accident_id = ?",
                accident.getId());
        jdbc.update("update accident set name = ?, text = ?, address = ? "
                        + "where accident.id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getId()
        );
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_rules(accident_id, rule_id) values(?, ?)",
                    accident.getId(),
                    rule.getId());
        }
    }

    public void delete(int id) {
        jdbc.update("delete from accident_rules where accident_id = ?", id);
        jdbc.update("delete from accident where id = ?", id);
    }
}
