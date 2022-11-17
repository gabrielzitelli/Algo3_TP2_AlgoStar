package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Ataque.Ataque;

public class SuperficieAerea extends Superficie {

    public Ataque conseguirTipoDeAtaque(Ataque unAtaque) {
        return unAtaque.ataqueAereo();
    }
}
