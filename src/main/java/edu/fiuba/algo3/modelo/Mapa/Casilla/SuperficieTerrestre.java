package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;

public class SuperficieTerrestre extends Superficie {
    @Override
    public Ataque conseguirTipoDeAtaque(Ataque unAtaque) {
        return unAtaque.ataqueTerrestre();
    }
}
