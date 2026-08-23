package edu.dosw.lab.comportamiento.reto7;

/**
 * Receptor: Módulo Motor del Rover.
 */
public class Motor {

    public String avanzar(int metros) {
        return "Rover avanza " + metros + " m";
    }

    public String retroceder(int metros) {
        return "Rover retrocede " + metros + " m";
    }
}
