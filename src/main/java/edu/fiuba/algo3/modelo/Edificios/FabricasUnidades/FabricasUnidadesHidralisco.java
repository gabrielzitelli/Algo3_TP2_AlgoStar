package edu.fiuba.algo3.modelo.Edificios.FabricasUnidades;

import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Hidralisco;

public class FabricasUnidadesHidralisco extends FabricasUnidades {

    private static final int poblacionQueOcupa = 2;

    public FabricasUnidadesHidralisco(){
        this.poblacionNecesaria = poblacionQueOcupa;
    }

    public static int obtenerPoblacionNecesaria(){
        return poblacionQueOcupa;
    }

    public Hidralisco crearUnidad(){
        return new Hidralisco();
    }
}
