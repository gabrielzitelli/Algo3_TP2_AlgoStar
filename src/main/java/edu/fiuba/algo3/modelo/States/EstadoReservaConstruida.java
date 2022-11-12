package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificio_Zerg.FabricaZerling;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;

public class EstadoReservaConstruida implements EstadoReserva{

    public EstadoReserva actualizar(){
        return this;
    }

    public FabricaZerling crearFabricaZerling(){
        return new FabricaZerling();
    }
}
