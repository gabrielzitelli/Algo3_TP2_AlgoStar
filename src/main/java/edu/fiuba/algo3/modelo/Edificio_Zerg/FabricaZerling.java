package edu.fiuba.algo3.modelo.Edificio_Zerg;

import edu.fiuba.algo3.modelo.UnidadesZerg.Zerling;

public class FabricaZerling implements Fabrica{
    public Zerling crearUnidad(){
        return new Zerling();
    }
}
