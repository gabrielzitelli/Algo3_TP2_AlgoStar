package edu.fiuba.algo3.modelo.Mapa.Casilla;

public abstract class Cargable {

    public abstract Cargable cargar();

    public boolean soyDiferenteA(Cargable cargableRequerido){
        return !this.getClass().equals(cargableRequerido.getClass());
    }

    public abstract Cargable descargar();
}
