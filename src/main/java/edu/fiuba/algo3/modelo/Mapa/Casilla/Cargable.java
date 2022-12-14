package edu.fiuba.algo3.modelo.Mapa.Casilla;

public abstract class Cargable {

    public abstract Cargable cargar();
    protected String identificador;

    public boolean soyDiferenteA(Cargable cargableRequerido){
        return !this.getClass().equals(cargableRequerido.getClass());
    }

    public abstract Cargable descargar();

    @Override
    public String toString() {
        return identificador;
    }
}
