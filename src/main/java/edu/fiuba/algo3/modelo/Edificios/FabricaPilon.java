package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Pilon;

public class FabricaPilon extends FabricaEdificio{

    @Override
    public Edificio crear() {
        Pilon unPilon = new Pilon();

        return unPilon;
    }
}
