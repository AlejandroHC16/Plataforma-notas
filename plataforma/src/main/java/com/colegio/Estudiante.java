package com.colegio;

// HERENCIA: Estudiante también hereda de Persona
public class Estudiante extends Persona {

    private String grado;

    public String getGrado() { return grado; }
    public void setGrado(String grado) { this.grado = grado; }

    // POLIMORFISMO: mismo método, resultado distinto al de Profesor
    @Override
    public String presentarse() {
        return "Estudiante " + getNombre() + " " + getApellido() + " - Grado: " + grado;
    }
}
