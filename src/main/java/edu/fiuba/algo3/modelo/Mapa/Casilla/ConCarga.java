package edu.fiuba.algo3.modelo.Mapa.Casilla;

public class ConCarga extends Cargable  {

    private int carga;

    public ConCarga(){
        this.identificador = "carga";
        carga = 1;
    }

    public Cargable cargar() {
        carga++;
        return this;
    }

    @Override
    public Cargable descargar() {
        if (carga > 1) {
            carga--;
            return this;
        }
        return new SinCarga();
    }
}
