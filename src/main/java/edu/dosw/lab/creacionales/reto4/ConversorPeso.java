package edu.dosw.lab.creacionales.reto4;

public final class ConversorPeso {

    private ConversorPeso() {
    }

    public static Pesaje convertir(double cantidad, String unidadOrigen, String unidadDestino) {
        double factorOrigen = FabricaUnidadPeso.obtenerFactor(unidadOrigen);
        double factorDestino = FabricaUnidadPeso.obtenerFactor(unidadDestino);

        double kilogramos = cantidad / factorOrigen;
        double cantidadConvertida = kilogramos * factorDestino;

        return new Pesaje(cantidad, unidadOrigen, cantidadConvertida, unidadDestino, kilogramos);
    }
}