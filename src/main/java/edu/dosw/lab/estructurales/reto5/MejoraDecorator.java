package edu.dosw.lab.estructurales.reto5;

public abstract class MejoraDecorator implements MotoComponent {

    protected final MotoComponent moto;

    protected MejoraDecorator(MotoComponent moto) {
        this.moto = moto;
    }
}