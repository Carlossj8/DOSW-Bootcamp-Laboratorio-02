package main.java.edu.dosw.lab.comportamiento.reto1;

/**
 * Línea de la orden: un producto y la cantidad pedida.
 * SRP: su única responsabilidad es calcular el subtotal de esa línea.
 */
public class ItemOrden {

    private final Producto producto;
    private int cantidad;

    public ItemOrden(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return producto.getPrecioUnitario() * cantidad;
    }

    public String getMensajeAgregado() {
        String unidad = cantidad == 1 ? "unidad" : "unidades";
        return String.format("%s: %d %s agregada%s a la orden.",
                producto.getNombre(), cantidad, unidad, cantidad == 1 ? "" : "s");
    }
}