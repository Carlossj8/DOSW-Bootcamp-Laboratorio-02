package edu.dosw.lab.creacionales.reto3;

public class FabricaVintage implements InstrumentoFactory {

    private static final double FACTOR = 5.0;
    private static final String AFINACION = "442 Hz";
    private static final String MATERIALES = "Piezas de época";

    @Override
    public Instrumento crearInstrumento(String familia, String modelo) {
        double precioBase = CatalogoInstrumentos.obtenerPrecioBase(modelo);
        double precio = precioBase * FACTOR;
        return new Instrumento(familia, modelo, "Vintage", MATERIALES, AFINACION, precio);
    }
}