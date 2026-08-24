package edu.dosw.lab.creacionales.reto3;

import java.util.Scanner;

public final class FabricaDeInstrumentos {

    private FabricaDeInstrumentos() {
    }

    public static void ejecutar(Scanner sc) {
        System.out.println("Bienvenido a Armonía Andina");

        int cantidad = leerEnteroPositivo(sc, "¿Cuántos instrumentos desea pedir? ");

        PedidoArmoniaAndina pedido = new PedidoArmoniaAndina();

        for (int i = 1; i <= cantidad; i++) {
            System.out.println();
            System.out.println("--- Instrumento " + i + " ---");

            String familia = leerFamilia(sc);
            String modelo  = leerModelo(sc, familia);
            String gama    = leerGama(sc);

            InstrumentoFactory fabrica = SelectorFabrica.obtenerFabrica(gama);
            Instrumento instrumento = fabrica.crearInstrumento(familia, modelo);
            pedido.agregarInstrumento(instrumento);
        }

        pedido.imprimirResumen();
    }

    // ── Lectura de familia ──────────────────────────────────────────────────
    private static String leerFamilia(Scanner sc) {
        System.out.println("  Familias: 1. Cuerda   2. Viento   3. Percusión");
        while (true) {
            System.out.print("  Familia: ");
            System.out.flush();
            if (!sc.hasNextLine()) return "Cuerda";
            String entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) continue;
            String norm = normalizar(entrada);
            if (norm.equals("1") || norm.contains("cuerda"))    return "Cuerda";
            if (norm.equals("2") || norm.contains("viento"))    return "Viento";
            if (norm.equals("3") || norm.contains("percusion")) return "Percusión";
            System.out.println("  Opción no válida. Ingrese 1, 2 o 3 (o el nombre de la familia).");
        }
    }

    // ── Lectura de modelo según familia ────────────────────────────────────
    private static String leerModelo(Scanner sc, String familia) {
        switch (familia) {
            case "Cuerda":
                System.out.println("  Modelos: 1. Guitarra ($800.000)   2. Violín ($1.600.000)   3. Bajo ($1.200.000)");
                break;
            case "Viento":
                System.out.println("  Modelos: 1. Saxofón ($2.500.000)   2. Flauta ($700.000)   3. Trompeta ($1.500.000)");
                break;
            default: // Percusión
                System.out.println("  Modelos: 1. Batería ($1.800.000)   2. Cajón ($350.000)   3. Timbal ($600.000)");
                break;
        }

        while (true) {
            System.out.print("  Modelo: ");
            System.out.flush();
            if (!sc.hasNextLine()) return modeloPorDefecto(familia);
            String entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) continue;
            String norm = normalizar(entrada);

            if (familia.equals("Cuerda")) {
                if (norm.equals("1") || norm.contains("guitarra")) return "Guitarra";
                if (norm.equals("2") || norm.contains("violin"))   return "Violín";
                if (norm.equals("3") || norm.contains("bajo"))     return "Bajo";
            } else if (familia.equals("Viento")) {
                if (norm.equals("1") || norm.contains("saxofon"))  return "Saxofón";
                if (norm.equals("2") || norm.contains("flauta"))   return "Flauta";
                if (norm.equals("3") || norm.contains("trompeta")) return "Trompeta";
            } else {
                if (norm.equals("1") || norm.contains("bateria"))  return "Batería";
                if (norm.equals("2") || norm.contains("cajon"))    return "Cajón";
                if (norm.equals("3") || norm.contains("timbal"))   return "Timbal";
            }
            System.out.println("  Modelo no válido para la familia " + familia + ". Ingrese 1, 2 o 3.");
        }
    }

    private static String modeloPorDefecto(String familia) {
        switch (familia) {
            case "Cuerda":   return "Guitarra";
            case "Viento":   return "Saxofón";
            default:         return "Batería";
        }
    }

    // ── Lectura de gama ────────────────────────────────────────────────────
    private static String leerGama(Scanner sc) {
        System.out.println("  Gamas: 1. Estudiante (×1,0)   2. Profesional (×3,0)   3. Vintage (×5,0)");
        while (true) {
            System.out.print("  Gama: ");
            System.out.flush();
            if (!sc.hasNextLine()) return "Estudiante";
            String entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) continue;
            String norm = normalizar(entrada);
            if (norm.equals("1") || norm.contains("estudiante"))  return "Estudiante";
            if (norm.equals("2") || norm.contains("profesional")) return "Profesional";
            if (norm.equals("3") || norm.contains("vintage"))     return "Vintage";
            System.out.println("  Opción no válida. Ingrese 1, 2 o 3 (o el nombre de la gama).");
        }
    }

    // ── Utilidades ─────────────────────────────────────────────────────────
    private static int leerEnteroPositivo(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            System.out.flush();
            if (!sc.hasNextLine()) return 1;
            String linea = sc.nextLine().trim();
            if (linea.isEmpty()) continue;
            try {
                int val = Integer.parseInt(linea);
                if (val > 0) return val;
                System.out.println("Por favor ingrese un número mayor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debe ser un número entero.");
            }
        }
    }

    private static String normalizar(String texto) {
        java.text.Normalizer.Form form = java.text.Normalizer.Form.NFD;
        String sinAcentos = java.text.Normalizer.normalize(texto.trim().toLowerCase(), form);
        return sinAcentos.replaceAll("\\p{M}", "");
    }
}