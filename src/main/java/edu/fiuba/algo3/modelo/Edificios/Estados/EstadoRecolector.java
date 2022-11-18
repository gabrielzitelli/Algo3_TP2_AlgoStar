package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;

public interface EstadoRecolector {

    EstadoRecolector actualizar();

    void extraer(Recurso recursoDelImperio, MaterialBruto materialBruto, int cantidadAExtraer);
}
