package edu.dosw.lab.comportamiento.reto6;

/**
 * Manejador concreto: Médico General.
 * Atiende pacientes con nivel MODERADO y prioridad máxima MEDIA (2).
 */
public class MedicoGeneral extends ProfesionalSalud {

    @Override
    public void procesar(Paciente paciente) {
        if (paciente.getNivel() == NivelGravedad.MODERADO && paciente.getPrioridad().getValor() <= Prioridad.MEDIA.getValor()) {
            paciente.marcarAtendido(getNombreCargo());
        } else {
            pasarAlSiguiente(paciente);
        }
    }

    @Override
    public String getNombreCargo() {
        return "Médico General";
    }
}
