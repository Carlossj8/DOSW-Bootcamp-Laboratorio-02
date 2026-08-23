package edu.dosw.lab.comportamiento.reto6;

/**
 * Representa a un paciente que ingresa a la sala de urgencias.
 */
public class Paciente {
    private final String id;
    private final String sintoma;
    private final NivelGravedad nivel;
    private final Prioridad prioridad;

    private boolean atendido;
    private String atendidoPor;
    private boolean remitido;

    public Paciente(String id, String sintoma, NivelGravedad nivel, Prioridad prioridad) {
        this.id = id;
        this.sintoma = sintoma;
        this.nivel = nivel;
        this.prioridad = prioridad;
        this.atendido = false;
        this.atendidoPor = null;
        this.remitido = false;
    }

    public String getId() {
        return id;
    }

    public String getSintoma() {
        return sintoma;
    }

    public NivelGravedad getNivel() {
        return nivel;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public boolean isAtendido() {
        return atendido;
    }

    public String getAtendidoPor() {
        return atendidoPor;
    }

    public boolean isRemitido() {
        return remitido;
    }

    public void marcarAtendido(String profesional) {
        this.atendido = true;
        this.atendidoPor = profesional;
        this.remitido = false;
    }

    public void marcarRemitido() {
        this.atendido = false;
        this.atendidoPor = null;
        this.remitido = true;
    }
}
