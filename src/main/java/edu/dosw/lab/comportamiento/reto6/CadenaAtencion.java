package edu.dosw.lab.comportamiento.reto6;

/**
 * Configura y gestiona la cadena de atención de la sala de urgencias.
 */
public class CadenaAtencion {

    private final ProfesionalSalud primerManejador;

    public CadenaAtencion() {
        ProfesionalSalud enfermero = new Enfermero();
        ProfesionalSalud medicoGeneral = new MedicoGeneral();
        ProfesionalSalud especialista = new Especialista();

        enfermero.setSiguiente(medicoGeneral);
        medicoGeneral.setSiguiente(especialista);

        this.primerManejador = enfermero;
    }

    /**
     * Envía un paciente a través de la cadena de responsabilidad.
     */
    public void atenderPaciente(Paciente paciente) {
        primerManejador.procesar(paciente);
    }
}
