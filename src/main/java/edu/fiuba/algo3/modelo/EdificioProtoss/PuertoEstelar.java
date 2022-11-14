package edu.fiuba.algo3.modelo.EdificioProtoss;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.States.EstadoPuertoEstelar;
import edu.fiuba.algo3.modelo.States.EstadoPuertoEstelarEnConstruccion;
import edu.fiuba.algo3.modelo.vida.VidaConEscudo;

public class PuertoEstelar extends Edificio {

    private Recolectable estadoRecolectable = new NoRecolectable();
    private Cargable estadoCarga = new ConCarga();
    private EstadoMoho estadoMoho = new SinMoho();
    private EstadoPuertoEstelar estado;
    private int turnoParaEstarConstruido = 10;
    private int valorVital = 600;

    public PuertoEstelar() {
        estado = new EstadoPuertoEstelarEnConstruccion(turnoParaEstarConstruido);
        this.vida = new VidaConEscudo(valorVital, valorVital);
    }

    public void pasarTurno() {
        estado = estado.actualizar();
        vida.pasarTurno();
    }

    public FabricaScout crearFabricaScout() {return estado.crearFabricaScout();}

    @Override
    public void verificarConstruccion(Casilla unaCasilla) {
        unaCasilla.tieneEsteMoho(estadoMoho);
        unaCasilla.tieneEsteRecoletable(estadoRecolectable);
        unaCasilla.tieneEstaCarga(estadoCarga);
    }
}
