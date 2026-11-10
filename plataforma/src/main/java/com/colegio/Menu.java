package com.colegio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// CommandLineRunner: Spring Boot ejecuta el método run() al arrancar
@Component
public class Menu implements CommandLineRunner {

    private final ProfesorDAO profesorDAO;
    private final EstudianteDAO estudianteDAO;
    private final MateriaDAO materiaDAO;
    private final NotaDAO notaDAO;
    private final Scanner teclado = new Scanner(System.in);

    public Menu(ProfesorDAO profesorDAO, EstudianteDAO estudianteDAO,
                MateriaDAO materiaDAO, NotaDAO notaDAO) {
        this.profesorDAO = profesorDAO;
        this.estudianteDAO = estudianteDAO;
        this.materiaDAO = materiaDAO;
        this.notaDAO = notaDAO;
    }

    @Override
    public void run(String... args) {
        int opcion;
        do {
            System.out.println("\n===== PLATAFORMA DEL COLEGIO =====");
            System.out.println("1. Registrar profesor");
            System.out.println("2. Registrar estudiante");
            System.out.println("3. Registrar materia");
            System.out.println("4. Registrar nota");
            System.out.println("5. Listar profesores");
            System.out.println("6. Listar estudiantes");
            System.out.println("7. Listar materias");
            System.out.println("8. Listar notas");
            System.out.println("9. Promedio de un estudiante");
            System.out.println("10. Ver todas las personas (polimorfismo)");
            System.out.println("0. Salir");
            opcion = leerEntero("Elige una opción: ");

            switch (opcion) {
                case 1 -> registrarProfesor();
                case 2 -> registrarEstudiante();
                case 3 -> registrarMateria();
                case 4 -> registrarNota();
                case 5 -> listarProfesores();
                case 6 -> listarEstudiantes();
                case 7 -> listarMaterias();
                case 8 -> notaDAO.listar().forEach(System.out::println);
                case 9 -> mostrarPromedio();
                case 10 -> verPersonas();
                case 0 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    // ---------- Registrar ----------
    private void registrarProfesor() {
        Profesor p = new Profesor();
        p.setNombre(leerTexto("Nombre: "));
        p.setApellido(leerTexto("Apellido: "));
        p.setEmail(leerTexto("Email: "));
        p.setEspecialidad(leerTexto("Especialidad: "));
        profesorDAO.guardar(p);
        System.out.println("Profesor guardado.");
    }

    private void registrarEstudiante() {
        Estudiante e = new Estudiante();
        e.setNombre(leerTexto("Nombre: "));
        e.setApellido(leerTexto("Apellido: "));
        e.setEmail(leerTexto("Email: "));
        e.setGrado(leerTexto("Grado: "));
        estudianteDAO.guardar(e);
        System.out.println("Estudiante guardado.");
    }

    private void registrarMateria() {
        listarProfesores();
        Materia m = new Materia();
        m.setNombre(leerTexto("Nombre de la materia: "));
        m.setCreditos(leerEntero("Créditos: "));
        m.setProfesorId(leerEntero("ID del profesor: "));
        try {
            materiaDAO.guardar(m);
            System.out.println("Materia guardada.");
        } catch (Exception ex) {
            System.out.println("Error: ese profesor no existe.");
        }
    }

    private void registrarNota() {
        listarEstudiantes();
        listarMaterias();
        Nota n = new Nota();
        n.setEstudianteId(leerEntero("ID del estudiante: "));
        n.setMateriaId(leerEntero("ID de la materia: "));
        double valor = leerDecimal("Nota (0 a 5): ");
        if (valor < 0 || valor > 5) {
            System.out.println("La nota debe estar entre 0 y 5.");
            return;
        }
        n.setValor(valor);
        try {
            notaDAO.guardar(n);
            System.out.println("Nota guardada.");
        } catch (Exception ex) {
            System.out.println("Error: el estudiante o la materia no existen.");
        }
    }

    // ---------- Listar ----------
    private void listarProfesores() {
        for (Profesor p : profesorDAO.listar()) {
            System.out.println(p.getId() + ". " + p.presentarse());
        }
    }

    private void listarEstudiantes() {
        for (Estudiante e : estudianteDAO.listar()) {
            System.out.println(e.getId() + ". " + e.presentarse());
        }
    }

    private void listarMaterias() {
        for (Materia m : materiaDAO.listar()) {
            System.out.println(m.getId() + ". " + m.getNombre() + " (" + m.getCreditos() + " créditos)");
        }
    }

    private void mostrarPromedio() {
        int id = leerEntero("ID del estudiante: ");
        Double promedio = notaDAO.promedio(id);
        if (promedio == null) {
            System.out.println("Ese estudiante no tiene notas.");
        } else {
            System.out.printf("Promedio: %.2f%n", promedio);
        }
    }

    // POLIMORFISMO: una sola lista de Persona con profesores y estudiantes.
    // Cada uno responde a presentarse() de forma diferente.
    private void verPersonas() {
        List<Persona> personas = new ArrayList<>();
        personas.addAll(profesorDAO.listar());
        personas.addAll(estudianteDAO.listar());
        for (Persona p : personas) {
            System.out.println(p.presentarse());
        }
    }

    // ---------- Leer datos del teclado ----------
    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine();
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Escribe un número entero.");
            }
        }
    }

    private double leerDecimal(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(teclado.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException ex) {
                System.out.println("Escribe un número (ej: 4.5).");
            }
        }
    }
}
