package edu.dosw.lab.creacionales.reto2;

import java.text.Normalizer;
import java.util.Scanner;

/**
 * Clase principal interactiva para el Reto 2: El Sastre a la Medida (Taller del Sastre).
 */
public final class SastreAMedida {

    private SastreAMedida() {
    }

    public static void ejecutar() {
        ejecutar(new Scanner(System.in));
    }

    public static void ejecutar(Scanner sc) {
        System.out.println("========================================");
        System.out.println(" Bienvenido al Taller del Sastre");
        System.out.println("========================================");
        System.out.println("Arma tu traje (Tela, Saco y Pantalón son obligatorios):\n");

        TrajeBuilder builder = new TrajeBuilder();

        // 1. Tela (Obligatoria)
        seleccionarTela(sc, builder);

        // 2. Saco (Obligatorio)
        seleccionarSaco(sc, builder);

        // 3. Pantalón (Obligatorio)
        seleccionarPantalon(sc, builder);

        // 4. Opcionales
        seleccionarOpcionales(sc, builder);

        Traje traje = builder.construir();

        // Salida final formateada
        mostrarResumenTraje(traje);
    }

    private static void seleccionarTela(Scanner sc, TrajeBuilder builder) {
        while (true) {
            System.out.print("¿Tela? (1. Lana italiana $320.000 / 2. Paño nacional $150.000) -> ");
            System.out.flush();
            if (!sc.hasNextLine()) {
                break;
            }
            String entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("La tela es obligatoria. Por favor elige una opción.");
                continue;
            }
            String norm = normalizar(entrada);
            if (norm.equals("1") || norm.contains("ITALIANA") || norm.contains("LANA")) {
                builder.conTela("Lana italiana", TrajeBuilder.PRECIO_LANA_ITALIANA);
                break;
            } else if (norm.equals("2") || norm.contains("NACIONAL") || norm.contains("PANO")) {
                builder.conTela("Paño nacional", TrajeBuilder.PRECIO_PANO_NACIONAL);
                break;
            } else {
                System.out.println("Opción de tela no válida. Ingrese 1 o 2 (o el nombre de la tela).");
            }
        }
    }

    private static void seleccionarSaco(Scanner sc, TrajeBuilder builder) {
        while (true) {
            System.out.print("¿Saco? (1. Cruzado $250.000 / 2. Recto $200.000) -> ");
            System.out.flush();
            if (!sc.hasNextLine()) {
                break;
            }
            String entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("El saco es obligatorio. Por favor elige una opción.");
                continue;
            }
            String norm = normalizar(entrada);
            if (norm.equals("1") || norm.contains("CRUZADO")) {
                builder.conSaco("Cruzado", TrajeBuilder.PRECIO_SACO_CRUZADO);
                break;
            } else if (norm.equals("2") || norm.contains("RECTO")) {
                builder.conSaco("Recto", TrajeBuilder.PRECIO_SACO_RECTO);
                break;
            } else {
                System.out.println("Opción de saco no válida. Ingrese 1 o 2.");
            }
        }
    }

    private static void seleccionarPantalon(Scanner sc, TrajeBuilder builder) {
        while (true) {
            System.out.print("¿Pantalón? (1. Corte slim $180.000 / 2. Corte clásico $160.000) -> ");
            System.out.flush();
            if (!sc.hasNextLine()) {
                break;
            }
            String entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("El pantalón es obligatorio. Por favor elige una opción.");
                continue;
            }
            String norm = normalizar(entrada);
            if (norm.equals("1") || norm.contains("SLIM")) {
                builder.conPantalon("Corte slim", TrajeBuilder.PRECIO_PANTALON_SLIM);
                break;
            } else if (norm.equals("2") || norm.contains("CLASICO")) {
                builder.conPantalon("Corte clásico", TrajeBuilder.PRECIO_PANTALON_CLASICO);
                break;
            } else {
                System.out.println("Opción de pantalón no válida. Ingrese 1 o 2.");
            }
        }
    }

    private static void seleccionarOpcionales(Scanner sc, TrajeBuilder builder) {
        // Chaleco
        System.out.print("¿Chaleco? ($90.000 - Ingrese acabado ej. Clásico, o Enter para omitir) -> ");
        System.out.flush();
        if (sc.hasNextLine()) {
            String chaleco = sc.nextLine().trim();
            if (!chaleco.isEmpty() && !esOmitir(chaleco)) {
                String desc = (chaleco.equals("1") || normalizar(chaleco).equals("SI")) ? "Clásico" : chaleco;
                builder.conChaleco(desc);
            }
        }

        // Forro en seda
        System.out.print("¿Forro en seda? ($70.000 - Ingrese tipo ej. Seda suave, o Enter para omitir) -> ");
        System.out.flush();
        if (sc.hasNextLine()) {
            String forro = sc.nextLine().trim();
            if (!forro.isEmpty() && !esOmitir(forro)) {
                String desc = (forro.equals("1") || normalizar(forro).equals("SI")) ? "Seda suave" : forro;
                builder.conForro(desc);
            }
        }

        // Bordado
        System.out.print("¿Bordado? ($35.000 - Ingrese texto ej. Iniciales, o Enter para omitir) -> ");
        System.out.flush();
        if (sc.hasNextLine()) {
            String bordado = sc.nextLine().trim();
            if (!bordado.isEmpty() && !esOmitir(bordado)) {
                String desc = (bordado.equals("1") || normalizar(bordado).equals("SI")) ? "Iniciales" : bordado;
                builder.conBordado(desc);
            }
        }
    }

    private static boolean esOmitir(String texto) {
        String norm = normalizar(texto);
        return norm.equals("NO") || norm.equals("OMITIR") || norm.equals("NINGUNO") || norm.equals("0");
    }

    private static void mostrarResumenTraje(Traje traje) {
        System.out.println("\n-------- Tu Traje --------");
        for (PiezaTraje pieza : traje.getPiezas()) {
            System.out.printf("%-10s %-15s %s%n",
                    pieza.getTipo() + ":",
                    pieza.getDescripcion(),
                    formatearPesos(pieza.getPrecio()));
        }

        System.out.println();
        System.out.printf("%-26s %s%n", "Total:", formatearPesos(traje.getTotal()));
        System.out.println("¡Lo esperamos en la prueba!");
    }

    private static String formatearPesos(double valor) {
        long redondeado = Math.round(valor);
        String digitos = String.valueOf(redondeado);
        StringBuilder resultado = new StringBuilder();
        int contador = 0;
        for (int i = digitos.length() - 1; i >= 0; i--) {
            resultado.insert(0, digitos.charAt(i));
            contador++;
            if (contador % 3 == 0 && i != 0) {
                resultado.insert(0, '.');
            }
        }
        return "$" + resultado;
    }

    private static String normalizar(String texto) {
        if (texto == null) return "";
        String norm = Normalizer.normalize(texto.trim(), Normalizer.Form.NFD);
        return norm.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toUpperCase();
    }
}