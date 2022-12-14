package edu.fiuba.algo3.modelo.Mapa.Casilla;

public class ConRevelar extends Revelable {

    private int cantidad;
    private final int cantidadInicial = 1;

    public ConRevelar() {
        this.cantidad = cantidadInicial;
    }

    @Override
    public Revelable revelar() {
        cantidad++;
        return this;
    }

    @Override
    public Revelable desRevelar() {
        if (cantidad > cantidadInicial) {
            cantidad--;
            return this;
        }

        return new SinRevelar();
    }
}
