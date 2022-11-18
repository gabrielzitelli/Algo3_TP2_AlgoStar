package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;

public class EstadoEdificioRecolectorEnConstruccion implements EstadoEdificioRecolector {

    private int turnoParaEstarConstruido;

    public EstadoEdificioRecolectorEnConstruccion(int turnoParaEstarConstruido) {
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    @Override
    public EstadoEdificioRecolector actualizar() {
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0)
            return new EstadoEdificioRecolectorConstruido();

        return this;
    }

    @Override
    public void extraer(Recurso recursoDelImperio, MaterialBruto materialBruto, int cantidadAExtraer) {
    }
}
