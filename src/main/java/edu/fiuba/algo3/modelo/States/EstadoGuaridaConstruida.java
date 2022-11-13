package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioZerg.FabricaHidralisco;

public class EstadoGuaridaConstruida implements EstadoGuarida {

    public EstadoGuarida actualizar(){
        return this;
    }
    public FabricaHidralisco crearFabricaHidralisco(){
        return new FabricaHidralisco();
    }
}