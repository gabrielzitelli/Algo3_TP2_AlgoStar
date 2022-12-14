package edu.fiuba.algo3.modelo.Edificios.FabricasEdificios;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Extractor;

public class FabricaExtractor extends FabricaEdificio {

    @Override
    public Edificio crear() {
        return new Extractor(gasDelImperio);
    }
}
