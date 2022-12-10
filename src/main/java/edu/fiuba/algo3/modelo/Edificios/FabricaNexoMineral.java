package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.NexoMineral;

public class FabricaNexoMineral extends FabricaEdificio{

    @Override
    public Edificio crear() {
        NexoMineral unNexoMineral = new NexoMineral(mineralesDelImperio);

        return unNexoMineral;
    }
}
