package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Ataque.Ataque;

public abstract class Superficie {

    protected String identificador;
    public abstract Ataque conseguirTipoDeAtaque(Ataque unAtaque);

    public abstract boolean puedeMoverse(Superficie superficieAComparar);

    public boolean soyDiferenteA(Superficie superficieRequerida){
        return !this.getClass().equals(superficieRequerida.getClass());
    }

    @Override
    public String toString() {
        return this.identificador;
    }
}
