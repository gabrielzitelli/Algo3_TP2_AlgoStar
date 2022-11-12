package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificio_Zerg.FabricaZerling;

public interface EstadoReserva {
    public abstract EstadoReserva actualizar();
    public abstract FabricaZerling crearFabricaZerling();
}
