CREATE TABLE IF NOT EXISTS profesores (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(100) NOT NULL,
    apellido     VARCHAR(100) NOT NULL,
    email        VARCHAR(120) NOT NULL,
    especialidad VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS estudiantes (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    nombre   VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email    VARCHAR(120) NOT NULL,
    grado    VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS materias (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(100) NOT NULL,
    creditos    INT NOT NULL,
    profesor_id INT,
    FOREIGN KEY (profesor_id) REFERENCES profesores(id)
);

CREATE TABLE IF NOT EXISTS notas (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id INT NOT NULL,
    materia_id    INT NOT NULL,
    valor         DOUBLE NOT NULL,
    FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id),
    FOREIGN KEY (materia_id) REFERENCES materias(id)
);
