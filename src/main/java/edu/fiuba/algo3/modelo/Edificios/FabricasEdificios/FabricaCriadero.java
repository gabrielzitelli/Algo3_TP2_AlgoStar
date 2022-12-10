package edu.fiuba.algo3.modelo.Edificios.FabricasEdificios;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;

public class FabricaCriadero extends FabricaEdificio {

    @Override
    public Edificio crear() {
        Criadero criadero = new Criadero();

        criadero.asignarListaDeUnidades(fabricasDisponibles);
        criadero.asignarListaDeUnidadesImperio(unidades);
        criadero.asignarRecursos(mineralesDelImperio, gasDelImperio);

        return criadero;
    }
}
