package edu.fiuba.algo3.modelo.EdificioProtoss;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.States.EstadoPilon;
import edu.fiuba.algo3.modelo.States.EstadoPilonEnConstruccion;

public class Pilon implements Edificio {

    private Recolectable estadoRecolectable = new NoRecolectable();
    private EstadoMoho estadoMoho = new SinMoho();
    private EstadoPilon estado;
    private int turnoParaEstarConstruido = 5;
    private Coordenada coordenada;

    public Pilon(){
        //Aplicacion de patron State
        estado = new EstadoPilonEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno(){
        estado = estado.actualizar(coordenada);
    }

    public void verificarConstruccion(Casilla unaCasilla){
        unaCasilla.tieneEsteRecoletable(estadoRecolectable);
        unaCasilla.tieneEsteMoho(estadoMoho);
        coordenada = unaCasilla.obtenerCoordenada();
    }
}
