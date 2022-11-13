package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificio_Zerg.FabricaMutalisco;

public class EstadoEspiralConstruida implements EstadoEspiral {

    @Override
    public EstadoEspiral actualizar() {
        return this;
    }

    @Override
    public FabricaMutalisco crearFabricaMutalisco() {
        return new FabricaMutalisco();
    }
}
