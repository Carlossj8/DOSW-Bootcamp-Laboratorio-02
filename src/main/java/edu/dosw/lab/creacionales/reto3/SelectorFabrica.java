package edu.dosw.lab.creacionales.reto3;

public final class SelectorFabrica {

    private SelectorFabrica() {
    }

    public static InstrumentoFactory obtenerFabrica(String gama) {
        String normalizada = gama.trim().toLowerCase();
        if (normalizada.contains("profesional")) {
            return new FabricaProfesional();
        }
        if (normalizada.contains("vintage")) {
            return new FabricaVintage();
        }
        return new FabricaEstudiante();
    }
}