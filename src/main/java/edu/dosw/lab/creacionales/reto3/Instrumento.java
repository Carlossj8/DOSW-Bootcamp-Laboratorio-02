package edu.dosw.lab.creacionales.reto3;

public class Instrumento {

    private final String familia;
    private final String modelo;
    private final String gama;
    private final String materiales;
    private final String afinacion;
    private final double precio;

    public Instrumento(String familia, String modelo, String gama, String materiales,
                       String afinacion, double precio) {
        this.familia = familia;
        this.modelo = modelo;
        this.gama = gama;
        this.materiales = materiales;
        this.afinacion = afinacion;
        this.precio = precio;
    }

    public String getFamilia() {
        return familia;
    }

    public String getModelo() {
        return modelo;
    }

    public String getGama() {
        return gama;
    }

    public String getMateriales() {
        return materiales;
    }

    public String getAfinacion() {
        return afinacion;
    }

    public double getPrecio() {
        return precio;
    }
}