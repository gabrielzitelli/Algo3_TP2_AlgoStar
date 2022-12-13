package edu.fiuba.algo3.modelo.Edificios.FabricasEdificios;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.PuertoEstelar;

public class FabricaPuertoEstelar extends FabricaEdificio {

    @Override
    public Edificio crear() {
        this.comprobarRequisitos(PuertoEstelar.requisitosEdilicios());

        PuertoEstelar unaPuertaEstelar = new PuertoEstelar();

        unaPuertaEstelar.asignarListaDeUnidades(fabricasDisponibles);
        unaPuertaEstelar.asignarListaDeUnidadesImperio(unidades);
        unaPuertaEstelar.asignarRecursos(mineralesDelImperio, gasDelImperio);

        return unaPuertaEstelar;
    }
}
