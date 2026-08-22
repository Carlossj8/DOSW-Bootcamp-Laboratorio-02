package edu.dosw.lab.creacionales.reto4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class BalanzaMercado {

    private BalanzaMercado() {
    }

    public static void ejecutar() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Balanza Honesta del Mercado");
        System.out.print("¿Cuántos pesajes? ");
        int cantidad = leerEntero(sc);

        List<Pesaje> pesajes = new ArrayList<>();

        for (int i = 1; i <= cantidad; i++) {
            System.out.print("P " + i + ": ");
            String linea = sc.nextLine().trim();

            String[] partes = linea.split("->");
            String parteIzquierda = partes[0].trim();
            String unidadDestino = partes[1].trim();

            int ultimoEspacio = parteIzquierda.lastIndexOf(' ');
            String cantidadTexto = parteIzquierda.substring(0, ultimoEspacio).trim();
            String unidadOrigen = parteIzquierda.substring(ultimoEspacio + 1).trim();

            double valor = FormateadorNumeros.analizarNumero(cantidadTexto);

            Pesaje pesaje = ConversorPeso.convertir(valor, unidadOrigen, unidadDestino);
            pesajes.add(pesaje);

            System.out.println("P " + i + ": "
                    + FormateadorNumeros.formatearCantidad(pesaje.getCantidadOriginal())
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

    private static int leerEntero(Scanner sc) {
        String linea = sc.nextLine().trim();
        try {
            return Integer.parseInt(linea);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}