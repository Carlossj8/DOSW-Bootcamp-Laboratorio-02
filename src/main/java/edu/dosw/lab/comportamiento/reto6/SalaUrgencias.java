package edu.dosw.lab.comportamiento.reto6;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Clase principal que ejecuta la simulación de la Sala de Urgencias del
 * Hospital San Rafael.
 */
public final class SalaUrgencias {

    private SalaUrgencias() {
    }

    public static void ejecutar() {
        ejecutar(new Scanner(System.in));
    }

    public static void ejecutar(Scanner sc) {
        System.out.println("========================================");
        System.out.println(" Hospital San Rafael - Sala de Urgencias");
        System.out.println("========================================");

        System.out.println("¿Cuántos pacientes ingresan a urgencias? ");

        int cantidad = leerCantidadPacientes(sc);
        if (cantidad <= 0) {
            System.out.println("No se ingresaron pacientes.");
            return;
        }

        List<Paciente> pacientes = new ArrayList<>();
        CadenaAtencion cadena = new CadenaAtencion();

        System.out.println("\n--- Ingreso de Pacientes ---");
        for (int i = 1; i <= cantidad; i++) {
            String idP = "P" + i;
            System.out.println("\n[" + idP + "]");

            System.out.print("  Síntoma / Dolencia: ");
            String sintoma = sc.nextLine().trim();
            if (sintoma.isEmpty()) {
                sintoma = "Dolencia no especificada";
            }

            System.out.print("  Nivel de gravedad (1. Leve / 2. Moderado / 3. Grave / 4. Crítico): ");
            String nivelInput = sc.nextLine().trim();
            NivelGravedad nivel = NivelGravedad.fromString(nivelInput);

            System.out.print("  Prioridad (1. Baja / 2. Media / 3. Alta): ");
            String priorInput = sc.nextLine().trim();
            Prioridad prioridad = Prioridad.fromString(priorInput);

            Paciente paciente = new Paciente(idP, sintoma, nivel, prioridad);
            cadena.atenderPaciente(paciente);
            pacientes.add(paciente);
        }

        // Mostrar resultados de atención
        System.out.println("\n--- Resultados de Atención ---");
        for (Paciente p : pacientes) {
            if (p.isAtendido()) {
                System.out.println(p.getId() + ": " + p.getAtendidoPor() + " atendió.");
            } else {
                System.out.println(p.getId() + ": Sin profesional disponible.");
                System.out.println("    Paciente remitido a otra institución.");
            }
        }

        // Estadísticas usando Streams
        mostrarEstadisticas(pacientes);
    }

    private static int leerCantidadPacientes(Scanner sc) {
        while (true) {
            System.out.print("¿Cuántos pacientes ingresan a urgencias? ");
            System.out.flush();
            if (!sc.hasNextLine()) {
                break;
            }
            String linea = sc.nextLine().trim();
            if (linea.isEmpty()) {
                continue;
            }
            try {
                int valor = Integer.parseInt(linea);
                if (valor > 0) {
                    return valor;
                }
                System.out.println("Por favor ingrese un número mayor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingrese un número entero.");
            }
        }
        return 0;
    }

    private static void mostrarEstadisticas(List<Paciente> pacientes) {
        System.out.println("\n--- Estadísticas ---");

        long leves = pacientes.stream()
                .filter(Paciente::isAtendido)
                .filter(p -> p.getNivel() == NivelGravedad.LEVE)
                .count();

        long moderados = pacientes.stream()
                .filter(Paciente::isAtendido)
                .filter(p -> p.getNivel() == NivelGravedad.MODERADO)
                .count();

        long graves = pacientes.stream()
                .filter(Paciente::isAtendido)
                .filter(p -> p.getNivel() == NivelGravedad.GRAVE)
                .count();

        long remitidos = pacientes.stream()
                .filter(Paciente::isRemitido)
                .count();

        double promedioPrioridad = pacientes.stream()
                .filter(Paciente::isAtendido)
                .mapToInt(p -> p.getPrioridad().getValor())
                .average()
                .orElse(0.0);

        System.out.println("Atendidos - Leve: " + leves + "  Moderado: " + moderados + "  Grave: " + graves);
        System.out.println("Remitidos a otra institución: " + remitidos);
        System.out.println("Promedio prioridad atendidos: " + String.format(Locale.US, "%.1f", promedioPrioridad));
    }
}
