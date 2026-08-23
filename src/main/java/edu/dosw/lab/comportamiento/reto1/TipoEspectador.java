package edu.dosw.lab.comportamiento.reto1;


/**
 * Define el algoritmo de cálculo de descuento, que varía según el tipo de espectador.
 * Permite agregar nuevos tipos de espectador sin modificar la clase Orden.
 */
public interface TipoEspectador {

    double calcularDescuento(double subtotal);

    String getNombre();
}