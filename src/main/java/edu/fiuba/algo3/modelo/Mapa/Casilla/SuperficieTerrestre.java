package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Ataque.Ataque;

public class SuperficieTerrestre extends Superficie {

    public SuperficieTerrestre(){
        this.identificador = "terrestre";
    }
    public Ataque conseguirTipoDeAtaque(Ataque unAtaque) {
        return unAtaque.ataqueTerrestre();
    }

    public boolean puedeMoverse(Superficie superficieAComparar) {
        return true;
    }
}
