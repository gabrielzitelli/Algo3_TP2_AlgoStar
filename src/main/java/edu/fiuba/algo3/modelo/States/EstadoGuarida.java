package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificio_Zerg.FabricaHidralisco;
import edu.fiuba.algo3.modelo.Edificio_Zerg.FabricaZerling;

public interface EstadoGuarida {

    public abstract EstadoGuarida actualizar();
    public abstract FabricaHidralisco crearFabricaHidralisco();
}
