package edu.fiuba.algo3.modelo.Mapa.Casilla;

public class ConCarga extends Cargable  {

    private int carga;

    public ConCarga(){
        carga = 1;
    }

    public Cargable cargar() {
        carga++;
        return this;
    }
}
