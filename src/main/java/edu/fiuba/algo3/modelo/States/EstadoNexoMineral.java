package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.MineralBruto;

public interface EstadoNexoMineral {
    public abstract EstadoNexoMineral actualizar();
    public abstract void extraer(Recurso mineralesDelImperio, MineralBruto nodoMineral);
}
