package edu.dosw.lab.comportamiento.reto6;

/**
 * Manejador concreto: Especialista.
 * Atiende pacientes con nivel GRAVE y prioridad máxima ALTA (3).
 */
public class Especialista extends ProfesionalSalud {

    @Override
    public void procesar(Paciente paciente) {
        if (paciente.getNivel() == NivelGravedad.GRAVE && paciente.getPrioridad().getValor() <= Prioridad.ALTA.getValor()) {
            paciente.marcarAtendido(getNombreCargo());
        } else {
            pasarAlSiguiente(paciente);
        }
    }

    @Override
    public String getNombreCargo() {
        return "Especialista";
    }
}
