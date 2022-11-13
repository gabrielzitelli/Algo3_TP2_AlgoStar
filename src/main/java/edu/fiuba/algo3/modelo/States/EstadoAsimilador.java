package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;

public interface EstadoAsimilador {
    public abstract EstadoAsimilador actualizar();
    public abstract void extraer(Recurso gasDelImperio, MaterialBruto volcanDeGas);
}
