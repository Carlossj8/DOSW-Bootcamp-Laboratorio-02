package edu.dosw.lab.comportamiento.reto7;

import java.text.Normalizer;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal interactiva para el Reto 7: Rover Explorador de Marte
 * (Chibchombo).
 */
public final class RoverExploradorMarte {

    private RoverExploradorMarte() {
    }

    public static void ejecutar() {
        ejecutar(new Scanner(System.in));
    }

    public static void ejecutar(Scanner sc) {
        System.out.println("========================================");
        System.out.println(" Rover Chibchombo - Control de Misión");
        System.out.println("========================================");

        Motor motor = new Motor();
        Brazo brazo = new Brazo();
        Camara camara = new Camara();
        Taladro taladro = new Taladro();

        ControlRover control = new ControlRover();

        int cantidadAcciones = leerEnteroPositivo(sc, "¿Cuántas acciones desea registrar? ");
        if (cantidadAcciones <= 0) {
            System.out.println("No se registraron acciones.");
            return;
        }

        System.out.println("\n--- Registro y Ejecución de Acciones ---");
        for (int i = 1; i <= cantidadAcciones; i++) {
            System.out.println("\n[Acción " + i + "]");
            System.out.print("  Operador: ");
            System.out.flush();
            String operador = leerTextoNoVacio(sc, "Operador anónimo");

            System.out.print("  Módulo (1. Motor / 2. Brazo / 3. Cámara / 4. Taladro): ");
            System.out.flush();
            String modulo = normalizar(sc.nextLine());

            Comando comando = crearComando(sc, modulo, operador, motor, brazo, camara, taladro);
            String respuesta = control.ejecutarComando(comando);
            System.out.println("  >> " + respuesta);
        }

        // Opción para deshacer acciones
        gestionarDeshacer(sc, control);

        // Mostrar historial final
        mostrarHistorial(control.getHistorial());
    }

    private static Comando crearComando(Scanner sc, String modulo, String operador,
            Motor motor, Brazo brazo, Camara camara, Taladro taladro) {
        switch (modulo) {
            case "1":
            case "MOTOR":
                return configurarMotor(sc, operador, motor);
            case "2":
            case "BRAZO":
                return configurarBrazo(sc, operador, brazo);
            case "3":
            case "CAMARA":
                return configurarCamara(sc, operador, camara);
            case "4":
            case "TALADRO":
                return configurarTaladro(sc, operador, taladro);
            default:
                System.out.println("  Módulo no reconocido, seleccionando Motor por defecto.");
                return configurarMotor(sc, operador, motor);
        }
    }

    private static Comando configurarMotor(Scanner sc, String operador, Motor motor) {
        System.out.print("  Acción Motor (1. Avanzar / 2. Retroceder): ");
        System.out.flush();
        String tipo = normalizar(sc.nextLine());
        String accion = (tipo.equals("2") || tipo.contains("RETROCEDER")) ? "Retroceder" : "Avanzar";
        int metros = leerEnteroPositivo(sc, "  Metros a desplazar: ");
        return new ComandoMotor(motor, operador, accion, metros);
    }

    private static Comando configurarBrazo(Scanner sc, String operador, Brazo brazo) {
        System.out.print("  Acción Brazo (1. Recoger / 2. Soltar): ");
        System.out.flush();
        String tipo = normalizar(sc.nextLine());
        String accion = (tipo.equals("2") || tipo.contains("SOLTAR")) ? "Soltar" : "Recoger";
        return new ComandoBrazo(brazo, operador, accion);
    }

    private static Comando configurarCamara(Scanner sc, String operador, Camara camara) {
        System.out.print("  Acción Cámara (1. Grabar / 2. Detener): ");
        System.out.flush();
        String tipo = normalizar(sc.nextLine());
        if (tipo.equals("2") || tipo.contains("DETENER")) {
            return new ComandoCamara(camara, operador, "Detener", 0);
        }
        int segundos = leerEnteroRango(sc, "  Segundos a grabar (0-120): ", 0, 120);
        return new ComandoCamara(camara, operador, "Grabar", segundos);
    }

    private static Comando configurarTaladro(Scanner sc, String operador, Taladro taladro) {
        System.out.print("  Acción Taladro (1. Perforar / 2. Retraer): ");
        System.out.flush();
        String tipo = normalizar(sc.nextLine());
        String accion = (tipo.equals("2") || tipo.contains("RETRAER")) ? "Retraer" : "Perforar";
        int profundidad = leerEnteroPositivo(sc, "  Profundidad (cm): ");
        return new ComandoTaladro(taladro, operador, accion, profundidad);
    }

    private static void gestionarDeshacer(Scanner sc, ControlRover control) {
        while (true) {
            System.out.print(
                    "\n¿Desea deshacer alguna acción? (Ingrese # de acción, ej. 3, o '0'/'Enter' para finalizar): ");
            System.out.flush();
            if (!sc.hasNextLine()) {
                break;
            }
            String linea = sc.nextLine().trim();
            if (linea.isEmpty() || linea.equals("0") || normalizar(linea).equals("NO")) {
                break;
            }

            String soloDigitos = linea.replaceAll("\\D+", "");
            if (soloDigitos.isEmpty()) {
                System.out.println("Entrada no válida.");
                continue;
            }

            try {
                int numAccion = Integer.parseInt(soloDigitos);
                String mensajeDeshacer = control.deshacerAccion(numAccion);
                System.out.println(mensajeDeshacer);
            } catch (NumberFormatException e) {
                System.out.println("Número no válido.");
            }
        }
    }

    private static void mostrarHistorial(List<Comando> historial) {
        System.out.println("\n--- Historial ---");
        for (int i = 0; i < historial.size(); i++) {
            Comando cmd = historial.get(i);
            String desc = cmd.isDeshecho() ? "[DESHECHO] " + cmd.getDescripcionFormatoHistorial()
                    : cmd.getDescripcionFormatoHistorial();
            System.out.printf("#%-2d %-32s - %s%n", (i + 1), desc, cmd.getOperador());
        }
    }

    private static int leerEnteroPositivo(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        System.out.flush();
        while (sc.hasNextLine()) {
            String linea = sc.nextLine().trim();
            if (linea.isEmpty()) {
                System.out.print(mensaje);
                System.out.flush();
                continue;
            }
            try {
                int val = Integer.parseInt(linea);
                if (val > 0) {
                    return val;
                }
                System.out.println("Por favor ingrese un número mayor a 0.");
                System.out.print(mensaje);
                System.out.flush();
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debe ser un número entero.");
                System.out.print(mensaje);
                System.out.flush();
            }
        }
        return 1;
    }

    private static int leerEnteroRango(Scanner sc, String mensaje, int min, int max) {
        System.out.print(mensaje);
        System.out.flush();
        while (sc.hasNextLine()) {
            String linea = sc.nextLine().trim();
            if (linea.isEmpty()) {
                System.out.print(mensaje);
                System.out.flush();
                continue;
            }
            try {
                int val = Integer.parseInt(linea);
                if (val >= min && val <= max) {
                    return val;
                }
                System.out.println("El valor debe estar entre " + min + " y " + max + ".");
                System.out.print(mensaje);
                System.out.flush();
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debe ser un número entero.");
                System.out.print(mensaje);
                System.out.flush();
            }
        }
        return min;
    }

    private static String leerTextoNoVacio(Scanner sc, String valorPorDefecto) {
        if (sc.hasNextLine()) {
            String linea = sc.nextLine().trim();
            return linea.isEmpty() ? valorPorDefecto : linea;
        }
        return valorPorDefecto;
    }

    private static String normalizar(String texto) {
        if (texto == null)
            return "";
        String norm = Normalizer.normalize(texto.trim(), Normalizer.Form.NFD);
        return norm.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toUpperCase();
    }
}
