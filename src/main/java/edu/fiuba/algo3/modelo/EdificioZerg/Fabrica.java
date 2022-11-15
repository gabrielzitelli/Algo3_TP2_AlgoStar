package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.Unidad;

public abstract class Fabrica {
    public abstract Unidad crearUnidad();

    public boolean esIgualA(Fabrica unaFabrica){
        return this.getClass().equals(unaFabrica.getClass());
    }
}
