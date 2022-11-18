package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;

public class EstadoEdificioRecolectorConstruido implements EstadoEdificioRecolector {

    @Override
    public EstadoEdificioRecolector actualizar() {
        return this;
    }

    @Override
    public void extraer(Recurso recursoDelImperio, MaterialBruto materialBruto, int cantidadAExtraer) {
        if (materialBruto != null)
            recursoDelImperio.depositar(materialBruto.extraer(cantidadAExtraer));
    }
}
