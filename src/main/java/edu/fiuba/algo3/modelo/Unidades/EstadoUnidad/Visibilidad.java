package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;

public interface Visibilidad {

    void recibirAtaque(Ataque unAtaque);

    Visibilidad verificarVisibilidadDe(Casilla unaCasilla);

    Visibilidad aumentarContador();
}
