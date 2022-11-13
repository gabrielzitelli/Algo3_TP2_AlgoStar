package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.States.EstadoEspiral;
import edu.fiuba.algo3.modelo.States.EstadoEspiralEnConstruccion;

public class Espiral implements Edificio {

    private EstadoEspiral estado;

    private int turnoParaEstarConstruido = 10;

    protected Recolectable estadoRecolectable = new NoRecolectable();
    protected Cargable estadoCarga = new SinCarga();
    protected EstadoMoho estadoMoho = new ConMoho();

    public Espiral() {
        estado = new EstadoEspiralEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno() {
        estado = estado.actualizar();
    }

    public FabricaMutalisco crearFabricaMutalisco() {
        return estado.crearFabricaMutalisco();
    }

    public void verificarConstruccion(Casilla unaCasilla){
        unaCasilla.tieneEsteRecoletable(estadoRecolectable);
        unaCasilla.tieneEstaCarga(estadoCarga);
        unaCasilla.tieneEsteMoho(estadoMoho);
    }
}
