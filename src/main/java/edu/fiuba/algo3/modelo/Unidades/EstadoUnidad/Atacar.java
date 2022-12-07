package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.Ataque.TipoDanio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;

public interface Atacar {

    void atacar(Casilla casillaAAtacar, TipoDanio unDanio);

}
