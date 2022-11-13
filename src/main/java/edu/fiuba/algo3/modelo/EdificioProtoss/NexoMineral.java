package edu.fiuba.algo3.modelo.EdificioProtoss;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.MineralBruto;
import edu.fiuba.algo3.modelo.States.EstadoNexoMineral;
import edu.fiuba.algo3.modelo.States.EstadoNexoMineralEnConstruccion;

public class NexoMineral {

    private EstadoNexoMineral estado;
    private int turnoParaEstarConstruido = 4;
    private Recurso mineralesDelImperio;
    private MineralBruto nodoMineral;

    public NexoMineral(Recurso mineralesDelImperio, MineralBruto nodoMineral) {
        this.nodoMineral = nodoMineral;
        this.mineralesDelImperio = mineralesDelImperio;
        estado = new EstadoNexoMineralEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno() {
        estado = estado.actualizar();
    }

    public void extraer() {
        estado.extraer(mineralesDelImperio, nodoMineral);
    }
}
