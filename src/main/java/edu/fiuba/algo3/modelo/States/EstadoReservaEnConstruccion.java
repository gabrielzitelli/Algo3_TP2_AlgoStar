package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioZerg.FabricaZerling;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;

public class EstadoReservaEnConstruccion implements EstadoReserva{

    private int turnoParaEstarConstruido;

    public EstadoReservaEnConstruccion(int turnoParaEstarConstruido){
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    public FabricaZerling crearFabricaZerling(){
        throw new ErrorEdificioNoEstaConstruido();
    }

    public EstadoReserva actualizar(){
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0)
            return new EstadoReservaConstruida();

        return this;
    }
}
