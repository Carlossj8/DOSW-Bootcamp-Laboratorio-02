package edu.dosw.lab.creacionales.reto2;

import java.util.ArrayList;
import java.util.List;

public class TrajeBuilder {

    private static final double PRECIO_LANA_ITALIANA = 320000;
    private static final double PRECIO_PANO_NACIONAL = 150000;
    private static final double PRECIO_SACO_CRUZADO = 250000;
    private static final double PRECIO_SACO_RECTO = 200000;
    private static final double PRECIO_PANTALON_SLIM = 180000;
    private static final double PRECIO_PANTALON_CLASICO = 160000;
    private static final double PRECIO_CHALECO = 90000;
    private static final double PRECIO_FORRO_SEDA = 70000;
    private static final double PRECIO_BORDADO = 35000;

    private final List<PiezaTraje> piezas = new ArrayList<>();

    public TrajeBuilder conTela(String opcion) {
        double precio = opcion.toLowerCase().contains("italiana")
                ? PRECIO_LANA_ITALIANA
                : PRECIO_PANO_NACIONAL;
        piezas.add(new PiezaTraje("Tela", opcion, precio));
        return this;
    }

    public TrajeBuilder conSaco(String opcion) {
        double precio = opcion.toLowerCase().contains("cruzado")
                ? PRECIO_SACO_CRUZADO
                : PRECIO_SACO_RECTO;
        piezas.add(new PiezaTraje("Saco", opcion, precio));
        return this;
    }

    public TrajeBuilder conPantalon(String opcion) {
        double precio = opcion.toLowerCase().contains("slim")
                ? PRECIO_PANTALON_SLIM
                : PRECIO_PANTALON_CLASICO;
        piezas.add(new PiezaTraje("Pantalón", opcion, precio));
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