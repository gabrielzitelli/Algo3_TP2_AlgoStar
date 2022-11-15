package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.UnidadesZerg.Hidralisco;

public class FabricaHidralisco extends Fabrica {

    public Hidralisco crearUnidad(){
        return new Hidralisco();
    }
}
