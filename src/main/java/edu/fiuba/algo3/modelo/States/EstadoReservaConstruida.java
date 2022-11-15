package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;
import edu.fiuba.algo3.modelo.EdificioZerg.FabricaZerling;

import java.util.ArrayList;

public class EstadoReservaConstruida implements EstadoReserva{

    public EstadoReserva actualizar(ArrayList<Fabrica> listaDeFabricasDisponibles){
        return this;
    }
    public FabricaZerling crearFabricaZerling(){
        return new FabricaZerling();
    }
}
