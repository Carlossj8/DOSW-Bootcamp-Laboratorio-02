package edu.dosw.lab.comportamiento.reto7;

/**
 * Comando concreto para controlar el Taladro del Rover.
 */
public class ComandoTaladro implements Comando {

    private final Taladro taladro;
    private final String operador;
    private final String accion; // "Perforar" o "Retraer"
    private final int profundidadCm;
    private boolean deshecho;

    public ComandoTaladro(Taladro taladro, String operador, String accion, int profundidadCm) {
        this.taladro = taladro;
        this.operador = operador;
        this.accion = accion;
        this.profundidadCm = profundidadCm;
        this.deshecho = false;
    }

    @Override
    public String ejecutar() {
        if ("Retraer".equalsIgnoreCase(accion)) {
            return taladro.retraer() + " [" + operador + "]";
        }
        return taladro.perforar(profundidadCm) + " [" + operador + "]";
    }

    @Override
    public String deshacer() {
        this.deshecho = true;
        if ("Retraer".equalsIgnoreCase(accion)) {
            return "Acción deshecha: " + taladro.perforar(profundidadCm) + ".";
        }
        return "Acción deshecha: " + taladro.retraer();
    }

    @Override
    public String getOperador() {
        return operador;
    }

    @Override
    public String getModulo() {
        return "Taladro";
    }

    @Override
    public String getDescripcionFormatoHistorial() {
        if ("Perforar".equalsIgnoreCase(accion)) {
            return "Taladro Perforar(" + profundidadCm + "cm)";
        }
        return "Taladro Retraer";
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
