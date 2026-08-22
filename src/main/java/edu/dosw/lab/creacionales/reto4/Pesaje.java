package edu.dosw.lab.creacionales.reto4;

public class Pesaje {

    private final double cantidadOriginal;
    private final String unidadOrigen;
    private final double cantidadConvertida;
    private final String unidadDestino;
    private final double kilogramosEquivalentes;

    public Pesaje(double cantidadOriginal, String unidadOrigen, double cantidadConvertida,
                  String unidadDestino, double kilogramosEquivalentes) {
        this.cantidadOriginal = cantidadOriginal;
        this.unidadOrigen = unidadOrigen;
        this.cantidadConvertida = cantidadConvertida;
        this.unidadDestino = unidadDestino;
        this.kilogramosEquivalentes = kilogramosEquivalentes;
    }

    public double getCantidadOriginal() {
        return cantidadOriginal;
    }

    public String getUnidadOrigen() {
        return unidadOrigen;
    }

    public double getCantidadConvertida() {
        return cantidadConvertida;
    }

    public String getUnidadDestino() {
        return unidadDestino;
    }

    public double getKilogramosEquivalentes() {
        return kilogramosEquivalentes;
    }
}