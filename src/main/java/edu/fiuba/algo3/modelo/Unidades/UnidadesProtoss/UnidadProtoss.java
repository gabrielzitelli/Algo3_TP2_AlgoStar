package edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss;

import edu.fiuba.algo3.modelo.Excepciones.ErrorFuegoCompañero;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

public abstract class UnidadProtoss extends Unidad {

    public boolean perteneceAImperio(Imperio imperio) {
        return imperio.getClass().equals(Protoss.class);
    }

    @Override
    protected void verificarFuegoAliado(Casilla casillaAAtacar) {
        if (casillaAAtacar.esFuegoAliado(new Protoss()))
            throw new ErrorFuegoCompañero();
    }
}

