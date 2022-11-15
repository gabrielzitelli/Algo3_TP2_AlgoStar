package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;

public abstract class Superficie {
    public abstract Ataque conseguirTipoDeAtaque(Ataque unAtaque);

    public boolean soyDiferenteA(Superficie superficieAComparar){
        return !this.getClass().equals(superficieAComparar.getClass());
    }

    public boolean soyIgualA(Superficie superficieAComparar){
        return this.getClass().equals(superficieAComparar.getClass());
    }
}
