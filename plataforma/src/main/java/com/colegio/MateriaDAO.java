package com.colegio;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MateriaDAO {

    private final JdbcTemplate jdbc;

    public MateriaDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void guardar(Materia m) {
        jdbc.update("INSERT INTO materias (nombre, creditos, profesor_id) VALUES (?, ?, ?)",
                m.getNombre(), m.getCreditos(), m.getProfesorId());
    }

    public List<Materia> listar() {
        return jdbc.query("SELECT * FROM materias", (rs, fila) -> {
            Materia m = new Materia();
            m.setId(rs.getInt("id"));
            m.setNombre(rs.getString("nombre"));
            m.setCreditos(rs.getInt("creditos"));
            m.setProfesorId(rs.getInt("profesor_id"));
            return m;
        });
    }
}
