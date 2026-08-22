package edu.dosw.lab.comportamiento.reto1;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Reto1BoleteriaAstor {


    public static void ejecutar() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenido al Cine Astor!");

        // 1. Tipo de espectador
        System.out.print("Espectador (General/Estudiante/Tercera edad): ");
        TipoEspectador tipoEspectador = leerTipoEspectador(sc.nextLine().trim());
        System.out.println("Espectador: " + tipoEspectador.getNombre());

        // 2. Cartelera
        Map<String, Producto> cartelera = new LinkedHashMap<>();
        cartelera.put("boleta2d", new Boleta("2D", 14_000));
        cartelera.put("boleta3d", new Boleta("3D", 22_000));
        cartelera.put("crispetas", new ArticuloConfiteria("Crispetas", 9_000));
        cartelera.put("gaseosa", new ArticuloConfiteria("Gaseosa", 4_500));

        System.out.println("\nCartelera y precios:");
        System.out.println("Boleta 2D $14.000");
        System.out.println("Boleta 3D $22.000");
        System.out.println("Crispetas $9.000");
        System.out.println("Gaseosa $4.500");

        // 3. Construcción de la orden
        Orden orden = new Orden(tipoEspectador);
        System.out.println("\nIngrese su orden (código cantidad). Escriba 'fin' para terminar.");
        System.out.println("Códigos: boleta2d, boleta3d, crispetas, gaseosa");

        while (true) {
            System.out.print("> ");
            String linea = sc.nextLine().trim();
            if (linea.equalsIgnoreCase("fin")) {
                break;
            }
            String[] partes = linea.split("\\s+");
            if (partes.length != 2 || !cartelera.containsKey(partes[0].toLowerCase())) {
                System.out.println("Entrada inválida. Ejemplo: boleta3d 2");
                continue;
            }
            try {
                Producto producto = cartelera.get(partes[0].toLowerCase());
                int cantidad = Integer.parseInt(partes[1]);
                ItemOrden item = orden.agregarItem(producto, cantidad);
                System.out.println(item.getMensajeAgregado());
            } catch (IllegalArgumentException e) {
                System.out.println("Cantidad inválida: " + e.getMessage());
            }
        }

        // 4. Factura final
        System.out.println();
        orden.imprimirFactura();
    }

    private static TipoEspectador leerTipoEspectador(String entrada) {
        String normalizada = entrada.toLowerCase();
        if (normalizada.contains("estudiante")) {
            return new Estudiante();
        }
        if (normalizada.contains("tercera")) {
            return new TerceraEdad();
        }
        return new EspectadorGeneral();
    }
}