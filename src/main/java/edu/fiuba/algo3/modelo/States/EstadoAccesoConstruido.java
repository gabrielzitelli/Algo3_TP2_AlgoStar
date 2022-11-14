package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioProtoss.FabricaDragon;
import edu.fiuba.algo3.modelo.EdificioProtoss.FabricaZealot;

public class EstadoAccesoConstruido implements EstadoAcceso {

    public EstadoAcceso actualizar(){
        return this;
    }

    public FabricaZealot crearFabricaZealot(){
        return new FabricaZealot();
    }

    public FabricaDragon crearFabricaDragon(){
        return new FabricaDragon();
    }
}
