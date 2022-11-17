package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;

public class FabricaZerling extends Fabrica {

    public Zerling crearUnidad(){
        return new Zerling();
    }
}
