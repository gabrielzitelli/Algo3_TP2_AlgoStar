package edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;

public abstract class EdificioProtoss extends Edificio {

    public boolean perteneceAImperio(Imperio imperio) {
        return imperio.getClass().equals(Protoss.class);
    }

    @Override
    public void verificarColocable(Casilla unaCasilla){
        super.verificarColocable(unaCasilla);
    }
}
