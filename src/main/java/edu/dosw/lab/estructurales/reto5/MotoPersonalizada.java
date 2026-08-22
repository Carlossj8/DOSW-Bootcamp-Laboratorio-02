package edu.dosw.lab.estructurales.reto5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class MotoPersonalizada {

    private static final String NOMBRE_MOTO_BASE = "Naked 250";
    private static final double PRECIO_MOTO_BASE = 9_800_000;

    private MotoPersonalizada() {
    }

    public static void ejecutar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Taller Turbo Andes");

        System.out.print("¿Cuántas motos desea personalizar? (Enter para 1) -> ");
        String cantidadTexto = sc.nextLine().trim();
        int cantidadMotos = cantidadTexto.isEmpty() ? 1 : Integer.parseInt(cantidadTexto);

        List<MotoArmada> motos = new ArrayList<>();
        for (int i = 1; i <= cantidadMotos; i++) {
            if (cantidadMotos > 1) {
                System.out.println();
                System.out.println("--- Moto " + i + " ---");
            }
            motos.add(personalizarMoto(sc));
        }

        System.out.println();
        motos.forEach(MotoPersonalizada::mostrarResumen);

        if (cantidadMotos > 1) {
            double totalGeneral = motos.stream()
                    .mapToDouble(m -> m.componente.getPrecio())
                    .sum();
            System.out.println("----- Total General -----");
            System.out.println("Total a pagar por todas las motos: " + formatearPesos(totalGeneral));
            System.out.println();
        }

        System.out.println("¡Buen viaje!");
    }

    private static MotoArmada personalizarMoto(Scanner sc) {
        System.out.println("Moto base: " + NOMBRE_MOTO_BASE + " (" + formatearPesos(PRECIO_MOTO_BASE) + ")");
        System.out.println("Elige tus mejoras:");

        CatalogoMejoras[] catalogo = CatalogoMejoras.values();
        for (int i = 0; i < catalogo.length; i++) {
            CatalogoMejoras mejora = catalogo[i];
            System.out.println((i + 1) + ". " + mejora.getNombre()
                    + " (+" + formatearPesos(mejora.getPrecioAdicional()) + ")");
        }

        System.out.print("Mejoras elegidas: ");
        String seleccion = sc.nextLine().trim();

        MotoComponent componente = new MotoBase(NOMBRE_MOTO_BASE, PRECIO_MOTO_BASE);
        List<CatalogoMejoras> elegidas = new ArrayList<>();

        if (!seleccion.isEmpty()) {
            for (String token : seleccion.split(",")) {
                int indice = Integer.parseInt(token.trim()) - 1;
                if (indice >= 0 && indice < catalogo.length) {
                    CatalogoMejoras mejora = catalogo[indice];
                    elegidas.add(mejora);
                    componente = new Mejora(componente, mejora.getNombre(), mejora.getPrecioAdicional());
                }
            }
        }

        return new MotoArmada(componente, elegidas);
    }

    private static void mostrarResumen(MotoArmada moto) {
        System.out.println("----- Tu Moto -----");
        System.out.println(NOMBRE_MOTO_BASE);
        moto.elegidas.forEach(m -> System.out.println("+ " + m.getNombre()));

        System.out.println("Descripción:");
        System.out.println(construirDescripcion(moto.elegidas));

        double mejoras = moto.elegidas.stream()
                .mapToDouble(CatalogoMejoras::getPrecioAdicional)
                .sum();

        System.out.println("Precio base: " + formatearPesos(PRECIO_MOTO_BASE));
        System.out.println("Mejoras: " + formatearPesos(mejoras));
        System.out.println("Total: " + formatearPesos(moto.componente.getPrecio()));
        System.out.println();
    }

    private static String construirDescripcion(List<CatalogoMejoras> elegidas) {
        if (elegidas.isEmpty()) {
            return NOMBRE_MOTO_BASE;
        }

        List<String> nombres = elegidas.stream()
                .map(m -> m.getNombre().toLowerCase())
                .collect(Collectors.toList());

        String listado;
        if (nombres.size() == 1) {
            listado = nombres.get(0);
        } else {
            String ultimo = nombres.get(nombres.size() - 1);
            String resto = String.join(", ", nombres.subList(0, nombres.size() - 1));
            listado = resto + " y " + ultimo;
        }

        return NOMBRE_MOTO_BASE + " con " + listado;
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


    private static final class MotoArmada {
        private final MotoComponent componente;
        private final List<CatalogoMejoras> elegidas;

        private MotoArmada(MotoComponent componente, List<CatalogoMejoras> elegidas) {
            this.componente = componente;
            this.elegidas = elegidas;
        }
    }
}