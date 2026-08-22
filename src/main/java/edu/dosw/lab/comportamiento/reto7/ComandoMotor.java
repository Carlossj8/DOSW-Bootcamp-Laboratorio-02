package edu.dosw.lab.comportamiento.reto7;

/**
 * Comando concreto para controlar el Motor del Rover.
 */
public class ComandoMotor implements Comando {

    private final Motor motor;
    private final String operador;
    private final String accion; // "Avanzar" o "Retroceder"
    private final int metros;
    private boolean deshecho;

    public ComandoMotor(Motor motor, String operador, String accion, int metros) {
        this.motor = motor;
        this.operador = operador;
        this.accion = accion;
        this.metros = metros;
        this.deshecho = false;
    }

    @Override
    public String ejecutar() {
        if ("Retroceder".equalsIgnoreCase(accion)) {
            return motor.retroceder(metros) + " [" + operador + "]";
        }
        return motor.avanzar(metros) + " [" + operador + "]";
    }

    @Override
    public String deshacer() {
        this.deshecho = true;
        if ("Retroceder".equalsIgnoreCase(accion)) {
            return "Acción deshecha: " + motor.avanzar(metros) + ".";
        }
        return "Acción deshecha: " + motor.retroceder(metros) + ".";
    }

    @Override
    public String getOperador() {
        return operador;
    }

    @Override
    public String getModulo() {
        return "Motor";
    }

    @Override
    public String getDescripcionFormatoHistorial() {
        return "Motor " + accion + "(" + metros + "m)";
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
