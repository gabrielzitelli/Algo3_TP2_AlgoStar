package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Acceso;

public class FabricaAcceso extends FabricaEdificio{

    @Override
    public Edificio crear() {
        Acceso unAcceso = new Acceso();

        unAcceso.asignarListaDeUnidades(fabricasDisponibles);
        unAcceso.asignarListaDeUnidadesImperio(unidades);
        unAcceso.asignarRecursos(mineralesDelImperio, gasDelImperio);

        return unAcceso;
    }
}
