package edu.dosw.lab.creacionales.reto4;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class FabricaUnidadPeso {

    private static final Map<String, Double> FACTORES_A_KG = new LinkedHashMap<>();

    static {
        FACTORES_A_KG.put("g", 1000.0);
        FACTORES_A_KG.put("lb", 2.2046);
        FACTORES_A_KG.put("@", 0.08);
        FACTORES_A_KG.put("kg", 1.0);
    }

    private FabricaUnidadPeso() {
    }

    public static boolean esValida(String codigo) {
        return codigo != null && FACTORES_A_KG.containsKey(normalizar(codigo));
    }

    public static double obtenerFactor(String codigo) {
        Double factor = FACTORES_A_KG.get(normalizar(codigo));
        if (factor == null) {
            throw new IllegalArgumentException("Unidad no reconocida: " + codigo);
        }
        return factor;
    }

    public static Set<String> obtenerUnidadesDisponibles() {
        return FACTORES_A_KG.keySet();
    }

    private static String normalizar(String codigo) {
        return codigo.trim().toLowerCase();
    }
}