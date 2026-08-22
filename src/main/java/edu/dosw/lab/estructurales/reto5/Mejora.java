package edu.dosw.lab.estructurales.reto5;

public class Mejora extends MejoraDecorator {

    private final String nombreMejora;
    private final double precioAdicional;

    public Mejora(MotoComponent moto, String nombreMejora, double precioAdicional) {
        super(moto);
        this.nombreMejora = nombreMejora;
        this.precioAdicional = precioAdicional;
    }

    public String getNombreMejora() {
        return nombreMejora;
    }

    @Override
    public String getDescripcion() {
        return moto.getDescripcion() + " + " + nombreMejora;
    }

    @Override
    public double getPrecio() {
        return moto.getPrecio() + precioAdicional;
    }
}