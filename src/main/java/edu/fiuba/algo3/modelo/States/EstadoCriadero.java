package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.UnidadesZerg.UnidadZerg;

public interface EstadoCriadero {
    public abstract UnidadZerg crearUnidad(Fabrica unaFabrica);
    public abstract EstadoCriadero actualizar(Coordenada coordenada);
}
