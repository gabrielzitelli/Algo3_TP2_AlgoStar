package edu.fiuba.algo3.modelo.Mapa.Casilla;

public class SinCarga extends Cargable{

    public Cargable cargar() {
        return new ConCarga();
    }

    @Override
    public Cargable descargar() {
        return this;
    }
}
