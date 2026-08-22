package edu.dosw.lab.comportamiento.reto1;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Orden de un espectador: agrupa ítems (boletas + confitería) y calcula
 * subtotal, descuento y total usando Streams.
 * SRP: coordina la orden, delega el cálculo de descuento en TipoEspectador (DIP).
 */
public class Orden {

    private final List<ItemOrden> items = new ArrayList<>();
    private final TipoEspectador tipoEspectador;

    public Orden(TipoEspectador tipoEspectador) {
        this.tipoEspectador = tipoEspectador;
    }

    public ItemOrden agregarItem(Producto producto, int cantidad) {
        ItemOrden item = new ItemOrden(producto, cantidad);
        items.add(item);
        return item;
    }

    public List<ItemOrden> getItems() {
        return List.copyOf(items);
    }

    public double calcularSubtotal() {
        return items.stream()
                .mapToDouble(ItemOrden::getSubtotal)
                .sum();
    }

    public double calcularDescuento() {
        return tipoEspectador.calcularDescuento(calcularSubtotal());
    }

    public double calcularTotal() {
        return calcularSubtotal() - calcularDescuento();
    }

    public void imprimirFactura() {
        double subtotal = calcularSubtotal();
        double descuento = calcularDescuento();
        int porcentaje = (int) Math.round((descuento / subtotal) * 100);

        System.out.println("------ FACTURA DE TAQUILLA ------");
        System.out.println("Espectador: " + tipoEspectador.getNombre());
        System.out.println();
        System.out.println("Ítems:");
        items.forEach(item -> System.out.println(
                formatearLinea(item.getProducto().getNombre(), item.getSubtotal())));
        System.out.println();
        System.out.println(formatearLinea("Subtotal", subtotal));
        System.out.println(formatearLinea("Descuento (" + porcentaje + "%)", descuento));
        System.out.println(formatearLinea("Total a pagar", calcularTotal()));
        System.out.println("----------------------------------");
        System.out.println("¡Disfrute la función!");
    }

    private static final Locale LOCALE_CO = new Locale("es", "CO");

    private String formatearLinea(String etiqueta, double valor) {
        return String.format(LOCALE_CO, "%s: $%,.0f", etiqueta, valor);
    }
}