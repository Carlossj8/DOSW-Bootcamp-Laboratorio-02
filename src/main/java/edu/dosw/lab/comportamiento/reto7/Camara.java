package edu.dosw.lab.comportamiento.reto7;

/**
 * Receptor: Módulo Cámara del Rover.
 */
public class Camara {

    public String grabar(int segundos) {
        return "Cámara grabando " + segundos + " s";
    }

    public String detener() {
        return "Cámara detiene la grabación";
    }
}
