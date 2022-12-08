package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.Ataque.TipoDanio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;

public interface Atacar {

    Atacar atacar(Coordenada coordenada, Casilla casillaAAtacar, TipoDanio unDanio);

    Atacar pasarTurno();
}
