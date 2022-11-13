package edu.fiuba.algo3.modelo.Mapa.Casilla;

public abstract class Cargable {
    public boolean soyDiferenteA(Cargable cargableRequerido){
        return !this.getClass().equals(cargableRequerido.getClass());
    }
}
