package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.UnidadesZerg.Mutalisco;

public class FabricaMutalisco extends Fabrica {

    @Override
    public Mutalisco crearUnidad() {
        return new Mutalisco();
    }
}
