package edu.dosw.lab.comportamiento.reto7;

/**
 * Comando concreto para controlar la Cámara del Rover.
 */
public class ComandoCamara implements Comando {

    private final Camara camara;
    private final String operador;
    private final String accion; // "Grabar" o "Detener"
    private final int segundos;
    private boolean deshecho;

    public ComandoCamara(Camara camara, String operador, String accion, int segundos) {
        this.camara = camara;
        this.operador = operador;
        this.accion = accion;
        this.segundos = segundos;
        this.deshecho = false;
    }

    @Override
    public String ejecutar() {
        if ("Detener".equalsIgnoreCase(accion)) {
            return camara.detener() + " [" + operador + "]";
        }
        return camara.grabar(segundos) + " [" + operador + "]";
    }

    @Override
    public String deshacer() {
        this.deshecho = true;
        if ("Detener".equalsIgnoreCase(accion)) {
            return "Acción deshecha: " + camara.grabar(segundos) + ".";
        }
        return "Acción deshecha: " + camara.detener() + ".";
    }

    @Override
    public String getOperador() {
        return operador;
    }

    @Override
    public String getModulo() {
        return "Cámara";
    }

    @Override
    public String getDescripcionFormatoHistorial() {
        if ("Grabar".equalsIgnoreCase(accion)) {
            return "Cámara Grabar(" + segundos + "s)";
        }
        return "Cámara Detener";
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
