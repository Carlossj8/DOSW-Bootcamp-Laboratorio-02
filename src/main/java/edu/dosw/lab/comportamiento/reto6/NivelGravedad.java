package edu.dosw.lab.comportamiento.reto6;

import java.text.Normalizer;

/**
 * Representa los niveles de gravedad de los síntomas de un paciente.
 */
public enum NivelGravedad {
    LEVE("Leve"),
    MODERADO("Moderado"),
    GRAVE("Grave"),
    CRITICO("Crítico"),
    OTRO("Otro");

    private final String nombre;

    NivelGravedad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Parsea un texto a NivelGravedad ignorando mayúsculas, minúsculas y tildes.
     */
    public static NivelGravedad fromString(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return OTRO;
        }
        String normalizado = normalizar(texto.trim());
        switch (normalizado) {
            case "1":
            case "LEVE":
                return LEVE;
            case "2":
            case "MODERADO":
                return MODERADO;
            case "3":
            case "GRAVE":
                return GRAVE;
            case "4":
            case "CRITICO":
                return CRITICO;
            default:
                return OTRO;
        }
    }

    private static String normalizar(String texto) {
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return normalizado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toUpperCase();
    }
}
