package edu.dosw.lab.comportamiento.reto6;

/**
 * Manejador base (Handler) en el patrón Chain of Responsibility.
 * Define la estructura para atender a un paciente o pasarlo al siguiente profesional.
 */
public abstract class ProfesionalSalud {

    protected ProfesionalSalud siguiente;

    public ProfesionalSalud setSiguiente(ProfesionalSalud siguiente) {
        this.siguiente = siguiente;
        return siguiente;
    }

    /**
     * Procesa la atención del paciente o lo remite al siguiente en la cadena.
     */
    public abstract void procesar(Paciente paciente);

    /**
     * Pasa el paciente al siguiente profesional disponible en la cadena.
     * Si no hay más profesionales, marca al paciente como remitido a otra institución.
     */
    protected void pasarAlSiguiente(Paciente paciente) {
        if (siguiente != null) {
            siguiente.procesar(paciente);
        } else {
            paciente.marcarRemitido();
        }
    }

    public abstract String getNombreCargo();
}
