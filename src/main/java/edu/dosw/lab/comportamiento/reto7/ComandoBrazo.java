package edu.dosw.lab.comportamiento.reto7;

/**
 * Comando concreto para controlar el Brazo robótico del Rover.
 */
public class ComandoBrazo implements Comando {

    private final Brazo brazo;
    private final String operador;
    private final String accion; // "Recoger" o "Soltar"
    private boolean deshecho;

    public ComandoBrazo(Brazo brazo, String operador, String accion) {
        this.brazo = brazo;
        this.operador = operador;
        this.accion = accion;
        this.deshecho = false;
    }

    @Override
    public String ejecutar() {
        if ("Soltar".equalsIgnoreCase(accion)) {
            return brazo.soltar() + " [" + operador + "]";
        }
        return brazo.recoger() + " [" + operador + "]";
    }

    @Override
    public String deshacer() {
        this.deshecho = true;
        if ("Soltar".equalsIgnoreCase(accion)) {
            return "Acción deshecha: " + brazo.recoger() + ".";
        }
        return "Acción deshecha: " + brazo.soltar() + ".";
    }

    @Override
    public String getOperador() {
        return operador;
    }

    @Override
    public String getModulo() {
        return "Brazo";
    }

    @Override
    public String getDescripcionFormatoHistorial() {
        return "Brazo " + accion;
    }

    @Override
    public boolean isDeshecho() {
        return deshecho;
    }

    @Override
    public void setDeshecho(boolean deshecho) {
        this.deshecho = deshecho;
    }
}
