package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Imperio.Zerg;

public abstract class EdificioZerg extends Edificio {

    public boolean perteneceAImperio(Imperio imperio) {
        return imperio.getClass().equals(Zerg.class);
    }
}
