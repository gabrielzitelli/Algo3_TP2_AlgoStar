package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.UnidadesZerg.Zerling;

public class FabricaZerling implements Fabrica{
    public Zerling crearUnidad(){
        return new Zerling();
    }
}
