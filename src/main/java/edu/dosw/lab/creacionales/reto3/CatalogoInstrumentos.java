package edu.dosw.lab.creacionales.reto3;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public final class CatalogoInstrumentos {

    private static final Map<String, Double> PRECIOS_BASE = new HashMap<>();

    static {
        PRECIOS_BASE.put("guitarra", 800000.0);
        PRECIOS_BASE.put("violin", 1600000.0);
        PRECIOS_BASE.put("bajo", 1200000.0);
        PRECIOS_BASE.put("saxofon", 2500000.0);
        PRECIOS_BASE.put("flauta", 700000.0);
        PRECIOS_BASE.put("trompeta", 1500000.0);
        PRECIOS_BASE.put("bateria", 1800000.0);
        PRECIOS_BASE.put("cajon", 350000.0);
        PRECIOS_BASE.put("timbal", 600000.0);
    }

    private CatalogoInstrumentos() {
    }

    public static double obtenerPrecioBase(String modelo) {
        String clave = normalizar(modelo);
        Double precio = PRECIOS_BASE.get(clave);
        if (precio == null) {
            throw new IllegalArgumentException("Modelo no reconocido: " + modelo);
        }
        return precio;
    }

    private static String normalizar(String texto) {
        String sinAcentos = Normalizer.normalize(texto.trim().toLowerCase(), Normalizer.Form.NFD);
        return sinAcentos.replaceAll("\\p{M}", "");
    }
}