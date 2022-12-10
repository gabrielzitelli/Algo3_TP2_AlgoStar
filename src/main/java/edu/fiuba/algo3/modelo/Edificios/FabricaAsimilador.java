package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Asimilador;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Pilon;

public class FabricaAsimilador extends FabricaEdificio{

    @Override
    public Edificio crear() {
        Asimilador unAsimilador = new Asimilador(gasDelImperio);

        return unAsimilador;
    }
}
