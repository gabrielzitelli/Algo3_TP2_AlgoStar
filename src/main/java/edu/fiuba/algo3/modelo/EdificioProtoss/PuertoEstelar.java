package edu.fiuba.algo3.modelo.EdificioProtoss;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.States.EstadoPuertoEstelar;
import edu.fiuba.algo3.modelo.States.EstadoPuertoEstelarEnConstruccion;

public class PuertoEstelar implements Edificio {

    private Recolectable estadoRecolectable = new NoRecolectable();
    private Cargable estadoCarga = new ConCarga();
    private EstadoMoho estadoMoho = new SinMoho();
    private EstadoPuertoEstelar estado;
    private int turnoParaEstarConstruido = 10;

    public PuertoEstelar() {
        estado = new EstadoPuertoEstelarEnConstruccion(turnoParaEstarConstruido);
    }
    public void pasarTurno() {estado = estado.actualizar();}
    public FabricaScout crearFabricaScout() {return estado.crearFabricaScout();}

    @Override
    public void verificarConstruccion(Casilla unaCasilla) {
        unaCasilla.tieneEsteMoho(estadoMoho);
        unaCasilla.tieneEsteRecoletable(estadoRecolectable);
        unaCasilla.tieneEstaCarga(estadoCarga);
    }
}
