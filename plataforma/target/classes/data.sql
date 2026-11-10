INSERT IGNORE INTO profesores (id, nombre, apellido, email, especialidad)
VALUES (1, 'Laura', 'Gómez', 'laura@colegio.com', 'Matemáticas');

INSERT IGNORE INTO estudiantes (id, nombre, apellido, email, grado)
VALUES (1, 'Ana', 'Martínez', 'ana@correo.com', '11A');

INSERT IGNORE INTO materias (id, nombre, creditos, profesor_id)
VALUES (1, 'Cálculo', 4, 1);

INSERT IGNORE INTO notas (id, estudiante_id, materia_id, valor)
VALUES (1, 1, 1, 4.5);
