package main.java.edu.dosw.lab.comportamiento.reto1;

/**
 * Representa un producto vendible en la boletería (boleta o artículo de confitería).
 * Es inmutable: una vez creado, ni el nombre ni el precio pueden cambiar.
 */
public abstract class Producto {

    private final String nombre;
    private final double precioUnitario;

    protected Producto(String nombre, double precioUnitario) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (precioUnitario < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }
}