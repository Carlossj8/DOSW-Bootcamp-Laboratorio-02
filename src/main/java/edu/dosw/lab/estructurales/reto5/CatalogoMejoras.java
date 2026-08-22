package edu.dosw.lab.estructurales.reto5;

public enum CatalogoMejoras {

    ESCAPE_DEPORTIVO("Escape deportivo", 1_400_000, "Accesorio"),
    MANILLAR_DEPORTIVO("Manillar deportivo", 480_000, "Accesorio"),
    LUCES_LED("Luces LED", 350_000, "Accesorio"),
    ALFORJAS_LATERALES("Alforjas laterales", 600_000, "Accesorio"),
    PINTURA_MATE_NEGRO("Pintura mate negro", 900_000, "Pintura"),
    PINTURA_METALIZADO_TRICAPA("Pintura metalizado tricapa", 1_600_000, "Pintura"),
    VINILO_PERSONALIZADO("Vinilo personalizado", 700_000, "Pintura"),
    GPS_INTEGRADO("GPS integrado", 1_100_000, "Complemento"),
    BAUL_TRASERO("Baúl trasero", 550_000, "Complemento"),
    SISTEMA_SONIDO("Sistema de sonido", 820_000, "Complemento");

    private final String nombre;
    private final double precioAdicional;
    private final String tipo;

    CatalogoMejoras(String nombre, double precioAdicional, String tipo) {
        this.nombre = nombre;
        this.precioAdicional = precioAdicional;
        this.tipo = tipo;
    }

    public String getNombre() { return nombre; }
    public double getPrecioAdicional() { return precioAdicional; }
    public String getTipo() { return tipo; }
}