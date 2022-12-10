package edu.fiuba.algo3.modelo.Edificios.FabricasEdificios;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.PuertoEstelar;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaEdificio;

public class FabricaPuertaEstelar extends FabricaEdificio {

    @Override
    public Edificio crear() {
        this.comprobarRequisitos(PuertoEstelar.requisitos());

        PuertoEstelar unaPuertaEstelar = new PuertoEstelar();

        unaPuertaEstelar.asignarListaDeUnidades(fabricasDisponibles);
        unaPuertaEstelar.asignarListaDeUnidadesImperio(unidades);
        unaPuertaEstelar.asignarRecursos(mineralesDelImperio, gasDelImperio);

        return unaPuertaEstelar;
    }
}
