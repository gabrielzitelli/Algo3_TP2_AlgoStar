package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Hidralisco;

public class FabricaHidralisco extends Fabrica {

    public FabricaHidralisco(){
        this.poblacionNecesaria = 2;
    }
    public Hidralisco crearUnidad(){
        return new Hidralisco();
    }
}
