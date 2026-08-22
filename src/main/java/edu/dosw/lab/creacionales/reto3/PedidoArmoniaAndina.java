package edu.dosw.lab.creacionales.reto3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PedidoArmoniaAndina {

    private final List<Instrumento> instrumentos = new ArrayList<>();

    public void agregarInstrumento(Instrumento instrumento) {
        instrumentos.add(instrumento);
    }

    public List<Instrumento> getInstrumentos() {
        return Collections.unmodifiableList(instrumentos);
    }

    public double getTotal() {
        return instrumentos.stream()
                .mapToDouble(Instrumento::getPrecio)
                .sum();
    }

    public void imprimirResumen() {
        int contador = 0;
        for (Instrumento instrumento : instrumentos) {
            contador++;
            System.out.println();
            System.out.println("Instrumento " + contador + ": " + instrumento.getModelo()
                    + " " + instrumento.getGama());
            System.out.println("Afinación: " + instrumento.getAfinacion());
            System.out.println("Precio: " + formatearPesos(instrumento.getPrecio()));
        }
        System.out.println();
        System.out.println("Total a pagar: " + formatearPesos(getTotal()));
        System.out.println("¡Gracias por su pedido!");
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