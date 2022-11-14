package edu.fiuba.algo3.modelo.EdificioProtoss;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.Mapa.MineralBruto;
import edu.fiuba.algo3.modelo.States.EstadoNexoMineral;
import edu.fiuba.algo3.modelo.States.EstadoNexoMineralEnConstruccion;
import edu.fiuba.algo3.modelo.vida.VidaConEscudo;

public class NexoMineral extends Edificio {

    private EstadoNexoMineral estado;
    private int turnoParaEstarConstruido = 4;
    private Recurso mineralesDelImperio;
    private MaterialBruto nodoMineral = null;

    private Recolectable estadoRecolectable = new MineralRecolectable();
    private EstadoMoho estadoMoho = new SinMoho();
    private int valorVital = 250;


    public NexoMineral(Recurso mineralesDelImperio) {
        this.vida = new VidaConEscudo(valorVital, valorVital);
        this.mineralesDelImperio = mineralesDelImperio;
        estado = new EstadoNexoMineralEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno() {
        estado = estado.actualizar();
        vida.pasarTurno();
    }

    public void extraer() {
        estado.extraer(mineralesDelImperio, nodoMineral);
    }

    public void verificarConstruccion(Casilla unaCasilla){
        unaCasilla.tieneEsteRecoletable(estadoRecolectable);
        unaCasilla.tieneEsteMoho(estadoMoho);
        establecerSobreMineral(unaCasilla.obtenerMaterial());
    }

    public void establecerSobreMineral(MaterialBruto nodoMineral){
        this.nodoMineral = nodoMineral;
    }
}
