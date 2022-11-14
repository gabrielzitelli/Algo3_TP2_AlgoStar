package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.States.EstadoEspiral;
import edu.fiuba.algo3.modelo.States.EstadoEspiralEnConstruccion;
import edu.fiuba.algo3.modelo.vida.VidaRegenerativa;

public class Espiral extends Edificio {

    private EstadoEspiral estado;

    private int turnoParaEstarConstruido = 10;

    protected Recolectable estadoRecolectable = new NoRecolectable();
    protected EstadoMoho estadoMoho = new ConMoho();
    private int valorVital = 1300;


    public Espiral() {
        this.vida = new VidaRegenerativa(valorVital);
        estado = new EstadoEspiralEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno() {
        estado = estado.actualizar();
        vida.pasarTurno();
    }

    public FabricaMutalisco crearFabricaMutalisco() {
        return estado.crearFabricaMutalisco();
    }

    public void verificarConstruccion(Casilla unaCasilla){
        unaCasilla.tieneEsteRecoletable(estadoRecolectable);
        unaCasilla.tieneEsteMoho(estadoMoho);
    }
}
