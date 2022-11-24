package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;

public class EstadoRecolectorEnConstruccion implements EstadoRecolector {

    private int turnoParaEstarConstruido;

    public EstadoRecolectorEnConstruccion(int turnoParaEstarConstruido) {
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    @Override
    public EstadoRecolector actualizar() {
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0)
            return new EstadoRecolectorConstruido();

        return this;
    }

    @Override
    public void extraer(Recurso recursoDelImperio, MaterialBruto materialBruto, int cantidadAExtraer) {
    }
}
