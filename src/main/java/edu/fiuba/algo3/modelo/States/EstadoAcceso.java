package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioProtoss.FabricaDragon;
import edu.fiuba.algo3.modelo.EdificioProtoss.FabricaZealot;

public interface EstadoAcceso {

    public abstract EstadoAcceso actualizar();
    public abstract FabricaDragon crearFabricaDragon();
    public abstract FabricaZealot crearFabricaZealot();
}
