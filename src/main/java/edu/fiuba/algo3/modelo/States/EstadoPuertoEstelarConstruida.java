package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificio_Protoss.FabricaScout;

public class EstadoPuertoEstelarConstruida implements EstadoPuertoEstelar {
    @Override
    public EstadoPuertoEstelar actualizar()  {return this;}

    @Override
    public FabricaScout crearFabricaScout() {
        return new FabricaScout();
    }
}
