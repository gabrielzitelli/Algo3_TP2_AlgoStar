package edu.fiuba.algo3.modelo.Mapa.Casilla;

public class SinRevelar extends Revelable {

    @Override
    public Revelable revelar() {
        return new ConRevelar();
    }

    @Override
    public Revelable desRevelar() {
        return this;
    }
}
