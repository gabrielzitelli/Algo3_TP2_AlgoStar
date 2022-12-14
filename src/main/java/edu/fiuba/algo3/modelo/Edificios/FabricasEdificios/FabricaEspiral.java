package edu.fiuba.algo3.modelo.Edificios.FabricasEdificios;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Espiral;

public class FabricaEspiral extends FabricaEdificio {

    @Override
    public Edificio crear() {
        this.comprobarRequisitos(Espiral.requisitosEdilicios());
        Espiral espiral = new Espiral();
        espiral.asignarListaDeUnidades(fabricasDisponibles);
        return espiral;
    }
}
