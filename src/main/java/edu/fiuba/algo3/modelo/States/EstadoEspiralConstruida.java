package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.FabricaMutalisco;

import java.util.ArrayList;

public class EstadoEspiralConstruida implements EstadoEspiral {

    public EstadoEspiral actualizar(ArrayList<Fabrica> listaDeFabricasDisponibles) {
        return this;
    }

    public FabricaMutalisco crearFabricaMutalisco() {
        return new FabricaMutalisco();
    }
}
