package edu.fiuba.algo3.modelo.Edificios.FabricasEdificios;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.NexoMineral;

public class FabricaNexoMineral extends FabricaEdificio {

    @Override
    public Edificio crear() {
        return new NexoMineral(mineralesDelImperio);
    }
}
