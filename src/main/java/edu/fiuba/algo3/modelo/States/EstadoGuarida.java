package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioZerg.FabricaHidralisco;

public interface EstadoGuarida {

    public abstract EstadoGuarida actualizar();
    public abstract FabricaHidralisco crearFabricaHidralisco();
}
