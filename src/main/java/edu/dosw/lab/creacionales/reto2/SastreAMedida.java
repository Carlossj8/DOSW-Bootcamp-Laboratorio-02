package edu.dosw.lab.creacionales.reto2;

import java.util.Scanner;
import edu.dosw.lab.creacionales.reto2.TrajeBuilder;

public final class SastreAMedida {

    private SastreAMedida() {
    }

    public static void ejecutar() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenido al Taller del Sastre");
        System.out.println("Arma tu traje:");

        System.out.print("¿Tela? -> ");
        String tela = sc.nextLine().trim();

        System.out.print("¿Saco? -> ");
        String saco = sc.nextLine().trim();

        System.out.print("¿Pantalón? -> ");
        String pantalon = sc.nextLine().trim();

        TrajeBuilder builder = new TrajeBuilder()
                .conTela(tela)
                .conSaco(saco)
                .conPantalon(pantalon);

        System.out.print("¿Chaleco? (Enter para omitir) -> ");
        String chaleco = sc.nextLine().trim();
        if (!chaleco.isEmpty()) {
            builder.conChaleco(chaleco);
        }

        System.out.print("¿Forro en seda? (Enter para omitir) -> ");
        String forro = sc.nextLine().trim();
        if (!forro.isEmpty()) {
            builder.conForro(forro);
        }

        System.out.print("¿Bordado? (Enter para omitir) -> ");
        String bordado = sc.nextLine().trim();
        if (!bordado.isEmpty()) {
            builder.conBordado(bordado);
        }

        Traje traje = builder.construir();

        System.out.println();
        System.out.println("------- Tu Traje -------");
        traje.getPiezas().forEach(pieza ->
                System.out.println(pieza.getTipo() + ": " + pieza.getDescripcion()
                        + " " + formatearPesos(pieza.getPrecio())));
        System.out.println("Total: " + formatearPesos(traje.getTotal()));
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
}