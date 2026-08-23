package edu.dosw.lab.creacionales.reto4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class BalanzaMercado {

    private BalanzaMercado() {
    }

    public static void ejecutar() {
        Scanner sc = new Scanner(System.in);

        System.out.println("=====================================");
        System.out.println(" Balanza Honesta del Mercado");
        System.out.println("=====================================");
        System.out.println("Unidades disponibles: " + String.join(", ", FabricaUnidadPeso.obtenerUnidadesDisponibles()));
        System.out.println("(g = gramo, lb = libra, @ = arroba, kg = kilogramo)");
        System.out.println();

        int cantidad = pedirCantidadPesajes(sc);
        List<Pesaje> pesajes = new ArrayList<>();

        for (int i = 1; i <= cantidad; i++) {
            System.out.println();
            System.out.println("--- Pesaje " + i + " de " + cantidad + " ---");

            double valor = pedirCantidad(sc);
            String unidadOrigen = pedirUnidad(sc, "Unidad de origen (g/lb/@/kg): ");
            String unidadDestino = pedirUnidad(sc, "Unidad de destino (g/lb/@/kg): ");

            Pesaje pesaje = ConversorPeso.convertir(valor, unidadOrigen, unidadDestino);
            pesajes.add(pesaje);

            System.out.println("-> " + FormateadorNumeros.formatearCantidad(pesaje.getCantidadOriginal())
                    + " " + pesaje.getUnidadOrigen() + " = "
                    + FormateadorNumeros.formatearCantidad(pesaje.getCantidadConvertida())
                    + " " + pesaje.getUnidadDestino());
        }

        double totalKg = pesajes.stream()
                .mapToDouble(Pesaje::getKilogramosEquivalentes)
                .sum();

        System.out.println();
        System.out.println("--- Resumen ---");
        System.out.println("Total kg equivalente: " + FormateadorNumeros.formatearCantidad(totalKg) + " kg");
        System.out.println("¡Gracias por comprar en la plaza!");
    }

    private static int pedirCantidadPesajes(Scanner sc) {
        while (true) {
            System.out.print("¿Cuántos pesajes desea calcular? ");
            String linea = sc.nextLine().trim();
            try {
                int valor = Integer.parseInt(linea);
                if (valor > 0) {
                    return valor;
                }
                System.out.println("Debe ingresar un número mayor que cero. Intente de nuevo.");
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un número entero válido (ej: 3). Intente de nuevo.");
            }
        }
    }

    private static double pedirCantidad(Scanner sc) {
        while (true) {
            System.out.print("Cantidad a convertir (ej: 2.500 o 40): ");
            String linea = sc.nextLine().trim();
            try {
                double valor = FormateadorNumeros.analizarNumero(linea);
                if (valor > 0) {
                    return valor;
                }
                System.out.println("La cantidad debe ser mayor que cero. Intente de nuevo.");
            } catch (NumberFormatException e) {
                System.out.println("Eso no es una cantidad válida. Escriba solo números (ej: 2.500). Intente de nuevo.");
            }
        }
    }

    private static String pedirUnidad(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String unidad = sc.nextLine().trim();
            if (FabricaUnidadPeso.esValida(unidad)) {
                return unidad;
            }
            System.out.println("Unidad no reconocida. Las unidades válidas son: "
                    + String.join(", ", FabricaUnidadPeso.obtenerUnidadesDisponibles()) + ". Intente de nuevo.");
        }
    }
}