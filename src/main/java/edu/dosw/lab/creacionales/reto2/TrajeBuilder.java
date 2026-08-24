package edu.dosw.lab.creacionales.reto2;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder para la construcción paso a paso de un Traje a la medida.
 */
public class TrajeBuilder {

    public static final double PRECIO_LANA_ITALIANA = 320000;
    public static final double PRECIO_PANO_NACIONAL = 150000;
    public static final double PRECIO_SACO_CRUZADO = 250000;
    public static final double PRECIO_SACO_RECTO = 200000;
    public static final double PRECIO_PANTALON_SLIM = 180000;
    public static final double PRECIO_PANTALON_CLASICO = 160000;
    public static final double PRECIO_CHALECO = 90000;
    public static final double PRECIO_FORRO_SEDA = 70000;
    public static final double PRECIO_BORDADO = 35000;

    private final List<PiezaTraje> piezas = new ArrayList<>();

    public TrajeBuilder conTela(String descripcion, double precio) {
        piezas.add(new PiezaTraje("Tela", descripcion, precio));
        return this;
    }

    public TrajeBuilder conSaco(String descripcion, double precio) {
        piezas.add(new PiezaTraje("Saco", descripcion, precio));
        return this;
    }

    public TrajeBuilder conPantalon(String descripcion, double precio) {
        piezas.add(new PiezaTraje("Pantalón", descripcion, precio));
        return this;
    }

    public TrajeBuilder conChaleco(String descripcion) {
        piezas.add(new PiezaTraje("Chaleco", descripcion, PRECIO_CHALECO));
        return this;
    }

    public TrajeBuilder conForro(String descripcion) {
        piezas.add(new PiezaTraje("Forro en seda", descripcion, PRECIO_FORRO_SEDA));
        return this;
    }

    public TrajeBuilder conBordado(String descripcion) {
        piezas.add(new PiezaTraje("Bordado", descripcion, PRECIO_BORDADO));
        return this;
    }

    public Traje construir() {
        return new Traje(new ArrayList<>(piezas));
    }
}