package edu.fiuba.algo3.modelo.Edificios.FabricasEdificios;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Guarida;

public class FabricaGuarida extends FabricaEdificio {
    @Override
    public Edificio crear() {
        this.comprobarRequisitos(Guarida.requisitosEdilicios());
        Guarida guarida = new Guarida();
        guarida.asignarListaDeUnidades(fabricasDisponibles);

        return guarida;
    }
}
