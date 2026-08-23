package edu.dosw.lab.comportamiento.reto1;


/** Estrategia concreta: estudiante, 15% de descuento. */
public class Estudiante implements TipoEspectador {

    private static final double PORCENTAJE_DESCUENTO = 0.15;

    @Override
    public double calcularDescuento(double subtotal) {
        return subtotal * PORCENTAJE_DESCUENTO;
    }

    @Override
    public String getNombre() {
        return "Estudiante";
    }
}