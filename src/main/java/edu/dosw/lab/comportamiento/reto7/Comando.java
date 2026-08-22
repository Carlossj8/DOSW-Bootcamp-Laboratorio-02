package edu.dosw.lab.comportamiento.reto7;

/**
 * Interfaz Command que declara los métodos para ejecutar y deshacer una acción en el Rover.
 */
public interface Comando {

    /**
     * Ejecuta la acción sobre el módulo correspondiente.
     * @return Mensaje descriptivo de la ejecución.
     */
    String ejecutar();

    /**
     * Deshace la acción sobre el módulo correspondiente.
     * @return Mensaje descriptivo del resultado al deshacer.
     */
    String deshacer();

    String getOperador();

    String getModulo();

    String getDescripcionFormatoHistorial();

    boolean isDeshecho();

    void setDeshecho(boolean deshecho);
}
