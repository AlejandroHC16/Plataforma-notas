package com.colegio;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotaDAO {

    private final JdbcTemplate jdbc;

    public NotaDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void guardar(Nota n) {
        jdbc.update("INSERT INTO notas (estudiante_id, materia_id, valor) VALUES (?, ?, ?)",
                n.getEstudianteId(), n.getMateriaId(), n.getValor());
    }

    // Devuelve textos listos para imprimir, uniendo las tablas con JOIN
    public List<String> listar() {
        String sql = "SELECT e.nombre AS estudiante, m.nombre AS materia, n.valor "
                   + "FROM notas n "
                   + "JOIN estudiantes e ON e.id = n.estudiante_id "
                   + "JOIN materias m ON m.id = n.materia_id";
        return jdbc.query(sql, (rs, fila) ->
                rs.getString("estudiante") + " | " + rs.getString("materia") + " | Nota: " + rs.getDouble("valor"));
    }

    public Double promedio(int estudianteId) {
        return jdbc.queryForObject("SELECT AVG(valor) FROM notas WHERE estudiante_id = ?",
                Double.class, estudianteId);
    }
}
