package edu.fiuba.algo3.modelo.Mapa.Casilla;

public class SinCarga extends Cargable{

    public SinCarga() {
        this.identificador = "ninguno";
    }
    public Cargable cargar() {
        return new ConCarga();
    }

    @Override
    public Cargable descargar() {
        return this;
    }
}
