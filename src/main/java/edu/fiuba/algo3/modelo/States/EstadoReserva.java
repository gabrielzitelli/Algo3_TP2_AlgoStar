package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioZerg.FabricaZerling;

public interface EstadoReserva {
    public abstract EstadoReserva actualizar();
    public abstract FabricaZerling crearFabricaZerling();
}
