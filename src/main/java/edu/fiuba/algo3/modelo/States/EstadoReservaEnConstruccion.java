package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.FabricaZerling;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;

import java.util.ArrayList;

public class EstadoReservaEnConstruccion implements EstadoReserva{

    private int turnoParaEstarConstruido;

    public EstadoReservaEnConstruccion(int turnoParaEstarConstruido){
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    public FabricaZerling crearFabricaZerling(){
        throw new ErrorEdificioNoEstaConstruido();
    }

    public EstadoReserva actualizar(ArrayList<Fabrica> listaDeFabricasDisponibles){
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0) {
            if (listaDeFabricasDisponibles != null)
                listaDeFabricasDisponibles.add(new FabricaZerling());

            return new EstadoReservaConstruida();
        }
        return this;
    }
}
