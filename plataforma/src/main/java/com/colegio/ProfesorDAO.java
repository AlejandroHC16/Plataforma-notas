package com.colegio;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

// DAO = clase que habla con la base de datos usando JDBC
@Repository
public class ProfesorDAO {

    private final JdbcTemplate jdbc;

    public ProfesorDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void guardar(Profesor p) {
        jdbc.update("INSERT INTO profesores (nombre, apellido, email, especialidad) VALUES (?, ?, ?, ?)",
                p.getNombre(), p.getApellido(), p.getEmail(), p.getEspecialidad());
    }

    public List<Profesor> listar() {
        // Por cada fila de la tabla creamos un objeto Profesor
        return jdbc.query("SELECT * FROM profesores", (rs, fila) -> {
            Profesor p = new Profesor();
            p.setId(rs.getInt("id"));
            p.setNombre(rs.getString("nombre"));
            p.setApellido(rs.getString("apellido"));
            p.setEmail(rs.getString("email"));
            p.setEspecialidad(rs.getString("especialidad"));
            return p;
        });
    }
}
