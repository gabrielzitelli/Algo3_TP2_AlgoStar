package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificio_Protoss.FabricaDragon;
import edu.fiuba.algo3.modelo.Edificio_Protoss.FabricaZealot;
import edu.fiuba.algo3.modelo.Edificio_Zerg.FabricaHidralisco;

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
