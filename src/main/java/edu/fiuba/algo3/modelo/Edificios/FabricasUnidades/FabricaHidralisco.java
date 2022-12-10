package edu.fiuba.algo3.modelo.Edificios.FabricasUnidades;

import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Hidralisco;

public class FabricaHidralisco extends Fabrica {

    private static int poblacionQueOcupa = 2;

    public FabricaHidralisco(){
        this.poblacionNecesaria = poblacionQueOcupa;
    }

    public static int obtenerPoblacionNecesaria(){
        return poblacionQueOcupa;
    }

    public Hidralisco crearUnidad(){
        return new Hidralisco();
    }
}
