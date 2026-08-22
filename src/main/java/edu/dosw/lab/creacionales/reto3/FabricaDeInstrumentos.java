package edu.dosw.lab.creacionales.reto3;

import java.util.Scanner;

public final class FabricaDeInstrumentos {

    private FabricaDeInstrumentos() {
    }

    public static void ejecutar() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenido a Armonía Andina");
        System.out.print("¿Cuántos instrumentos desea pedir? ");
        int cantidad = leerEntero(sc);

        PedidoArmoniaAndina pedido = new PedidoArmoniaAndina();

        for (int i = 1; i <= cantidad; i++) {
            System.out.println();
            System.out.println("Instrumento " + i + ":");

            System.out.print("Familia: ");
            String familia = sc.nextLine().trim();

            System.out.print("Modelo: ");
            String modelo = sc.nextLine().trim();

            System.out.print("Gama: ");
            String gama = sc.nextLine().trim();

            InstrumentoFactory fabrica = SelectorFabrica.obtenerFabrica(gama);
            Instrumento instrumento = fabrica.crearInstrumento(familia, modelo);
            pedido.agregarInstrumento(instrumento);
        }

        pedido.imprimirResumen();
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