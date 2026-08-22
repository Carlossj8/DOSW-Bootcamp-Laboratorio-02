package edu.dosw.lab.creacionales.reto2;

import java.util.Collections;
import java.util.List;

public class Traje {

    private final List<PiezaTraje> piezas;

    Traje(List<PiezaTraje> piezas) {
        this.piezas = Collections.unmodifiableList(piezas);
    }

    public List<PiezaTraje> getPiezas() {
        return piezas;
    }

    public double getTotal() {
        return piezas.stream()
                .mapToDouble(PiezaTraje::getPrecio)
                .sum();
    }
}
