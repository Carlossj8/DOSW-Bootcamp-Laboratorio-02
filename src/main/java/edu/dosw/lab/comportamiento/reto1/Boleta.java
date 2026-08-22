package edu.dosw.lab.comportamiento.reto1;


/**
 * Boleta de cine
 * Polimorfismo: se trata igual que cualquier otro Producto dentro de la Orden,
 */
public class Boleta extends Producto {

    public Boleta(String tipo, double precioUnitario) {
        super("Boleta " + tipo, precioUnitario);
    }
}