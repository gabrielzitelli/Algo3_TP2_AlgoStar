package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;

public interface EstadoPilon {

    public abstract EstadoPilon actualizar(Coordenada coordenada);
}
