package edu.fiuba.algo3.modelo.Edificios.FabricasEdificios;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Asimilador;

public class FabricaAsimilador extends FabricaEdificio {

    @Override
    public Edificio crear() {
        return new Asimilador(gasDelImperio);
    }
}
