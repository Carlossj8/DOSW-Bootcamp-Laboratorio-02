package edu.dosw.lab.comportamiento.reto6;

import java.text.Normalizer;

/**
 * Representa la prioridad de atención médica y su valor numérico asociado.
 */
public enum Prioridad {
    BAJA("Baja", 1),
    MEDIA("Media", 2),
    ALTA("Alta", 3);

    private final String nombre;
    private final int valor;

    Prioridad(String nombre, int valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public int getValor() {
        return valor;
    }

    /**
     * Parsea un texto a Prioridad ignorando mayúsculas, minúsculas y tildes.
     */
    public static Prioridad fromString(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return BAJA;
        }
        String normalizado = normalizar(texto.trim());
        switch (normalizado) {
            case "1":
            case "BAJA":
                return BAJA;
            case "2":
            case "MEDIA":
            case "MEDIO":
                return MEDIA;
            case "3":
            case "ALTA":
            case "ALTO":
                return ALTA;
            default:
                return BAJA;
        }
    }

    private static String normalizar(String texto) {
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return normalizado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toUpperCase();
    }
}
