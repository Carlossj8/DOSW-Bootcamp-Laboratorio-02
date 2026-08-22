package edu.dosw.lab.creacionales.reto4;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class FormateadorNumeros {

    private FormateadorNumeros() {
    }

    public static double analizarNumero(String texto) {
        String limpio = texto.trim();
        if (limpio.matches("\\d{1,3}(\\.\\d{3})+")) {
            limpio = limpio.replace(".", "");
        } else {
            limpio = limpio.replace(",", ".");
        }
        return Double.parseDouble(limpio);
    }

    public static String formatearCantidad(double valor) {
        BigDecimal redondeado = BigDecimal.valueOf(valor).setScale(3, RoundingMode.HALF_UP).stripTrailingZeros();
        if (redondeado.scale() < 0) {
            redondeado = redondeado.setScale(0);
        }

        String texto = redondeado.toPlainString();
        String parteEntera;
        String parteDecimal;
        int puntoIndex = texto.indexOf('.');
        if (puntoIndex >= 0) {
            parteEntera = texto.substring(0, puntoIndex);
            parteDecimal = texto.substring(puntoIndex + 1);
        } else {
            parteEntera = texto;
            parteDecimal = "";
        }

        boolean negativo = parteEntera.startsWith("-");
        if (negativo) {
            parteEntera = parteEntera.substring(1);
        }

        StringBuilder enteraFormateada = new StringBuilder();
        int contador = 0;
        for (int i = parteEntera.length() - 1; i >= 0; i--) {
            enteraFormateada.insert(0, parteEntera.charAt(i));
            contador++;
            if (contador % 3 == 0 && i != 0) {
                enteraFormateada.insert(0, '.');
            }
        }

        String resultado = (negativo ? "-" : "") + enteraFormateada;
        if (!parteDecimal.isEmpty()) {
            resultado += "," + parteDecimal;
        }
        return resultado;
    }
}
