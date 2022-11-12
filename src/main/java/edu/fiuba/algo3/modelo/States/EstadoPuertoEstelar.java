package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificio_Protoss.FabricaScout;

public interface EstadoPuertoEstelar {
    EstadoPuertoEstelar actualizar();

    FabricaScout crearFabricaScout();
}
