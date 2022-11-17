package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Ataque.Ataque;

public interface Superficie {

    Ataque conseguirTipoDeAtaque(Ataque unAtaque);

    boolean puedeMoverse(Superficie superficieAComparar);
}
