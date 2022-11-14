package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.UnidadesZerg.UnidadZerg;

public interface Fabrica {
    public abstract UnidadZerg crearUnidad();
}
