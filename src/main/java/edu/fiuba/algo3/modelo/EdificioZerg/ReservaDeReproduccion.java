package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.States.EstadoReserva;
import edu.fiuba.algo3.modelo.States.EstadoReservaEnConstruccion;
import edu.fiuba.algo3.modelo.vida.VidaRegenerativa;

public class ReservaDeReproduccion extends Edificio {

    private Recolectable estadoRecolectable = new NoRecolectable();
    private Cargable estadoCarga = new SinCarga();
    private EstadoMoho estadoMoho = new ConMoho();
    private EstadoReserva estado;
    private int turnoParaEstarConstruido = 12;
    private int valorVital = 1000;


    public ReservaDeReproduccion(){
        estado = new EstadoReservaEnConstruccion(turnoParaEstarConstruido);
        this.vida = new VidaRegenerativa(valorVital);
    }

    public void pasarTurno(){
        estado = estado.actualizar();
        vida.pasarTurno();
    }

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
