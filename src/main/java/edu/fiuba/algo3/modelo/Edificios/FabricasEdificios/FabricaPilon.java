package edu.fiuba.algo3.modelo.Edificios.FabricasEdificios;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Pilon;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaEdificio;

public class FabricaPilon extends FabricaEdificio {

    @Override
    public Edificio crear() {
        Pilon unPilon = new Pilon();

        return unPilon;
    }
}
