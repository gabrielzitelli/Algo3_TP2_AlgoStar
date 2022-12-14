package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;

public interface Caminar {

    Caminar caminar(Coordenada coordenadaInicial, Coordenada coordenadaDestino);

    Caminar pasarTurno();
}
