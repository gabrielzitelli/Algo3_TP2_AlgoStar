package edu.fiuba.algo3.modelo.Edificios.FabricasEdificios;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Pilon;

public class FabricaPilon extends FabricaEdificio {

    @Override
    public Edificio crear() {
        return new Pilon();
    }
}
