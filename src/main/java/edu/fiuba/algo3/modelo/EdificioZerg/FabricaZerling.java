package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.UnidadesZerg.Zerling;

public class FabricaZerling extends Fabrica {
    public Zerling crearUnidad(){
        return new Zerling();
    }
}
