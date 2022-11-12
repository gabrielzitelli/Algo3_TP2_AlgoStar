package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.GasBruto;

public interface EstadoAsimilador {
    public abstract EstadoAsimilador actualizar();
    public abstract void extraer(Recurso gasDelImperio, GasBruto volcanDeGas);
}
