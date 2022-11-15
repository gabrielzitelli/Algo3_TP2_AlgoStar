package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.States.EstadoReserva;
import edu.fiuba.algo3.modelo.States.EstadoReservaEnConstruccion;
import edu.fiuba.algo3.modelo.vida.VidaRegenerativa;

public class ReservaDeReproduccion extends Edificio {

    private EstadoReserva estado;
    private int turnoParaEstarConstruido = 12;
    private int valorVital = 1000;


    public ReservaDeReproduccion(){
        this.costoMineral = 150;
        this.costoGas = 0;
        this.estadoMoho = new ConMoho();
        this.estadoRecolectable = new NoRecolectable();
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
}
