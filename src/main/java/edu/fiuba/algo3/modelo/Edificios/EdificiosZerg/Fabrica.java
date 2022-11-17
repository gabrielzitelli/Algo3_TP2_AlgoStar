package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Unidades.Unidad;

public abstract class Fabrica {

    public abstract Unidad crearUnidad();

    public boolean esIgualA(Fabrica unaFabrica){
        return this.getClass().equals(unaFabrica.getClass());
    }
}
