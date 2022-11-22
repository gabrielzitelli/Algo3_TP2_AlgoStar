package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;

public interface Visibilidad {

    void recibirAtaque(Ataque unAtaque);

    void establecerCoordenada(Coordenada coordenada);

    Visibilidad aumentarContador();
}
