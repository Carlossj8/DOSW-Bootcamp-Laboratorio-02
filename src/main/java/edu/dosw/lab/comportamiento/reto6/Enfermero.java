package edu.dosw.lab.comportamiento.reto6;

/**
 * Manejador concreto: Enfermero.
 * Atiende pacientes con nivel LEVE y prioridad máxima BAJA (1).
 */
public class Enfermero extends ProfesionalSalud {

    @Override
    public void procesar(Paciente paciente) {
        if (paciente.getNivel() == NivelGravedad.LEVE && paciente.getPrioridad().getValor() <= Prioridad.BAJA.getValor()) {
            paciente.marcarAtendido(getNombreCargo());
        } else {
            pasarAlSiguiente(paciente);
        }
    }

    @Override
    public String getNombreCargo() {
        return "Enfermero";
    }
}
