package edu.fiuba.algo3.modelo.Mapa.Casilla;

public abstract class EstadoMoho {

    public boolean soyDiferenteA(EstadoMoho mohoRequerido){
        return !this.getClass().equals(mohoRequerido.getClass());
    }
}
