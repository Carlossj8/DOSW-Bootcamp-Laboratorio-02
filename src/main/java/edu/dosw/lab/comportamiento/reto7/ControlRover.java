package edu.dosw.lab.comportamiento.reto7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Invocador (Invoker) del patrón Command.
 * Gestiona la ejecución, registro en historial y la reversión (undo) de comandos del Rover.
 */
public class ControlRover {

    private final List<Comando> historial;

    public ControlRover() {
        this.historial = new ArrayList<>();
    }

    /**
     * Ejecuta el comando y lo registra en el historial.
     */
    public String ejecutarComando(Comando comando) {
        historial.add(comando);
        return comando.ejecutar();
    }

    /**
     * Deshace una acción específica por su número en el historial (1-indexed).
     */
    public String deshacerAccion(int numeroAccion) {
        int indice = numeroAccion - 1;
        if (indice < 0 || indice >= historial.size()) {
            return "No existe la acción #" + numeroAccion + " en el historial.";
        }

        Comando comando = historial.get(indice);
        if (comando.isDeshecho()) {
            return "La acción #" + numeroAccion + " ya ha sido deshecha previamente.";
        }

        return comando.deshacer();
    }

    public List<Comando> getHistorial() {
        return Collections.unmodifiableList(historial);
    }
}
