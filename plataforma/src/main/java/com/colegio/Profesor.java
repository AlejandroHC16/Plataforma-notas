package com.colegio;

// HERENCIA: Profesor hereda todo lo de Persona
public class Profesor extends Persona {

    private String especialidad;

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    // POLIMORFISMO: sobrescribimos el método de Persona
    @Override
    public String presentarse() {
        return "Profesor " + getNombre() + " " + getApellido() + " - Especialidad: " + especialidad;
    }
}
