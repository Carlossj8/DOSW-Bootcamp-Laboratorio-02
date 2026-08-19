package main.java.edu.dosw.lab.comportamiento.reto1;


/** Estrategia concreta: tercera edad, 25% de descuento. */
public class TerceraEdad implements TipoEspectador {

    private static final double PORCENTAJE_DESCUENTO = 0.25;

    @Override
    public double calcularDescuento(double subtotal) {
        return subtotal * PORCENTAJE_DESCUENTO;
    }

    @Override
    public String getNombre() {
        return "Tercera edad";
    }
}