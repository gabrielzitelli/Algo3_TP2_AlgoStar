package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Guarida;

public class FabricaGuarida extends FabricaEdificio{
    @Override
    public Edificio crear() {
        this.comprobarRequisitos(Guarida.requisitos());
        Guarida guarida = new Guarida();
        guarida.asignarListaDeUnidades(fabricasDisponibles);

        return guarida;
    }
}
