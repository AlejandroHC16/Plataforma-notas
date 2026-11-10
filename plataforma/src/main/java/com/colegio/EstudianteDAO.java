package com.colegio;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstudianteDAO {

    private final JdbcTemplate jdbc;

    public EstudianteDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void guardar(Estudiante e) {
        jdbc.update("INSERT INTO estudiantes (nombre, apellido, email, grado) VALUES (?, ?, ?, ?)",
                e.getNombre(), e.getApellido(), e.getEmail(), e.getGrado());
    }

    public List<Estudiante> listar() {
        return jdbc.query("SELECT * FROM estudiantes", (rs, fila) -> {
            Estudiante e = new Estudiante();
            e.setId(rs.getInt("id"));
            e.setNombre(rs.getString("nombre"));
            e.setApellido(rs.getString("apellido"));
            e.setEmail(rs.getString("email"));
            e.setGrado(rs.getString("grado"));
            return e;
        });
    }
}
