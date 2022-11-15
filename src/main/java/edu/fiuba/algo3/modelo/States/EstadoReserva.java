package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;
import edu.fiuba.algo3.modelo.EdificioZerg.FabricaZerling;

import java.util.ArrayList;

public interface EstadoReserva {
    public abstract EstadoReserva actualizar(ArrayList<Fabrica> listaDeFabricasDisponibles);
    public abstract FabricaZerling crearFabricaZerling();
}
