package edu.dosw.lab.creacionales.reto4;

import java.util.HashMap;
import java.util.Map;

public final class FabricaUnidadPeso {

    private static final Map<String, Double> FACTORES_A_KG = new HashMap<>();

    static {
        FACTORES_A_KG.put("g", 1000.0);
        FACTORES_A_KG.put("lb", 2.2046);
        FACTORES_A_KG.put("@", 0.08);
        FACTORES_A_KG.put("kg", 1.0);
    }

    private FabricaUnidadPeso() {
    }

    public static double obtenerFactor(String codigo) {
        String clave = codigo.trim().toLowerCase();
        Double factor = FACTORES_A_KG.get(clave);
        if (factor == null) {
            throw new IllegalArgumentException("Unidad no reconocida: " + codigo);
        }
        return factor;
    }
}