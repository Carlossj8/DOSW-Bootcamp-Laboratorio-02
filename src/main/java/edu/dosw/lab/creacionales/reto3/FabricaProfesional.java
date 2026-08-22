package edu.dosw.lab.creacionales.reto3;

public class FabricaProfesional implements InstrumentoFactory {

    private static final double FACTOR = 3.0;
    private static final String AFINACION = "440 Hz";
    private static final String MATERIALES = "Maderas selectas";

    @Override
    public Instrumento crearInstrumento(String familia, String modelo) {
        double precioBase = CatalogoInstrumentos.obtenerPrecioBase(modelo);
        double precio = precioBase * FACTOR;
        return new Instrumento(familia, modelo, "Profesional", MATERIALES, AFINACION, precio);
    }
}