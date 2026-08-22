package edu.dosw.lab.estructurales.reto5;

public class MotoBase implements MotoComponent {

    private final String nombre;
    private final double precioBase;

    public MotoBase(String nombre, double precioBase) {
        this.nombre = nombre;
        this.precioBase = precioBase;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String getDescripcion() {
        return nombre;
    }

    @Override
    public double getPrecio() {
        return precioBase;
    }
}