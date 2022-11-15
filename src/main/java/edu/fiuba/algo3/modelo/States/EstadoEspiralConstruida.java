package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;
import edu.fiuba.algo3.modelo.EdificioZerg.FabricaMutalisco;

import java.util.ArrayList;

public class EstadoEspiralConstruida implements EstadoEspiral {

    @Override
    public EstadoEspiral actualizar(ArrayList<Fabrica> listaDeFabricasDisponibles) {
        return this;
    }

    @Override
    public FabricaMutalisco crearFabricaMutalisco() {
        return new FabricaMutalisco();
    }
}
