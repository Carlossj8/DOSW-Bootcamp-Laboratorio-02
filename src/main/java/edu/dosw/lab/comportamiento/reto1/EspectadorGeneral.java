package main.java.edu.dosw.lab.comportamiento.reto1;


/** Estrategia concreta: espectador general, sin descuento. */
public class EspectadorGeneral implements TipoEspectador {

    @Override
    public double calcularDescuento(double subtotal) {
        return 0.0;
    }

    @Override
    public String getNombre() {
        return "General";
    }
}