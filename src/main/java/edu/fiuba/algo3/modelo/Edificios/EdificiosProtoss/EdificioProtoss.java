package edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

public abstract class EdificioProtoss extends Edificio {

    public boolean perteneceAImperio(Imperio imperio) {
        return imperio.getClass().equals(Protoss.class);
    }

    @Override
    public void verificarConstruccion(Casilla unaCasilla){
        super.verificarConstruccion(unaCasilla);
    }

    public boolean somosAliados(Unidad unaUnidad){
        return unaUnidad.perteneceAImperio(new Protoss());
    }
}
