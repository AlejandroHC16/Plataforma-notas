package com.colegio;

public class Nota {

    private int id;
    private int estudianteId;
    private int materiaId;
    private double valor;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEstudianteId() { return estudianteId; }
    public void setEstudianteId(int estudianteId) { this.estudianteId = estudianteId; }

    public int getMateriaId() { return materiaId; }
    public void setMateriaId(int materiaId) { this.materiaId = materiaId; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
}
