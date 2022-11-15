package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;
import edu.fiuba.algo3.modelo.EdificioZerg.FabricaHidralisco;

import java.util.ArrayList;

public class EstadoGuaridaConstruida implements EstadoGuarida {

    public EstadoGuarida actualizar(ArrayList<Fabrica> listaDeFabricasDisponibles){
        return this;
    }
    public FabricaHidralisco crearFabricaHidralisco(){
        return new FabricaHidralisco();
    }
}