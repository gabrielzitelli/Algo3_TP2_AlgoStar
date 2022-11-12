package edu.fiuba.algo3.modelo.Edificio_Zerg;

import edu.fiuba.algo3.modelo.States.EstadoReserva;
import edu.fiuba.algo3.modelo.States.EstadoReservaEnConstruccion;

public class ReservaDeProduccion {

    private EstadoReserva estado;
    private int turnoParaEstarConstruido = 12;

    public ReservaDeProduccion(){
        estado = new EstadoReservaEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno(){estado = estado.actualizar();}

    public FabricaZerling crearFabricaZerling(){
        return estado.crearFabricaZerling();
    }
}
