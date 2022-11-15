package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;

public interface Superficie {
    Ataque conseguirTipoDeAtaque(Ataque unAtaque);
}
