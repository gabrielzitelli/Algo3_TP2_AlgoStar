package edu.fiuba.algo3.modelo.Mapa.Casilla;

public abstract class EstadoMoho {

    protected String identificador;
    @Override
    public String toString() {
        return identificador;
    }

    public boolean soyDiferenteA(EstadoMoho mohoRequerido){
        return !this.getClass().equals(mohoRequerido.getClass());
    }
}
