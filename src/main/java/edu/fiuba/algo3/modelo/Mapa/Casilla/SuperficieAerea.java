package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;

public class SuperficieAerea implements Superficie {
    @Override
    public Ataque conseguirTipoDeAtaque(Ataque unAtaque) {
        return unAtaque.ataqueAereo();
    }
}
