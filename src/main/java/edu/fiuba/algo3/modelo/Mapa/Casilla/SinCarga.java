package edu.fiuba.algo3.modelo.Mapa.Casilla;

public class SinCarga extends Cargable{
    @Override
    public Cargable cargar() {
        return new ConCarga();
    }
}
