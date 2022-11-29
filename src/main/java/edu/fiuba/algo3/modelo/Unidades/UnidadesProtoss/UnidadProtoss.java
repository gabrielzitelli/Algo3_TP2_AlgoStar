package edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss;

import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

public abstract class UnidadProtoss extends Unidad {

    public boolean perteneceAImperio(Imperio imperio) {
        return imperio.getClass().equals(Protoss.class);
    }
}

