package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.States.EstadoReserva;
import edu.fiuba.algo3.modelo.States.EstadoReservaEnConstruccion;

public class ReservaDeReproduccion implements Edificio {

    private Recolectable estadoRecolectable = new NoRecolectable();
    private Cargable estadoCarga = new SinCarga();
    private EstadoMoho estadoMoho = new ConMoho();
    private EstadoReserva estado;
    private int turnoParaEstarConstruido = 12;

    public ReservaDeReproduccion(){
        estado = new EstadoReservaEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno(){estado = estado.actualizar();}

    public FabricaZerling crearFabricaZerling(){
        return estado.crearFabricaZerling();
    }

    @Override
    public void verificarConstruccion(Casilla unaCasilla) {
        unaCasilla.tieneEsteRecoletable(estadoRecolectable);
        unaCasilla.tieneEstaCarga(estadoCarga);
        unaCasilla.tieneEsteMoho(estadoMoho);
    }
}
