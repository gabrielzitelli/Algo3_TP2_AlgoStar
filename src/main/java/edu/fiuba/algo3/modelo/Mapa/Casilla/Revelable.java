package edu.fiuba.algo3.modelo.Mapa.Casilla;

public abstract class Revelable {

    public abstract Revelable revelar();

    public abstract Revelable desRevelar();

    public boolean esIgualA(Revelable unRevelable) {
        return this.getClass().equals(unRevelable.getClass());
    }
}
