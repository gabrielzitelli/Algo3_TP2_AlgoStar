package edu.fiuba.algo3.modelo.Edificios.FabricasUnidades;

import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Mutalisco;

public class FabricasUnidadesMutalisco extends FabricasUnidades {

    private static final int poblacionQueOcupa = 4;

    public FabricasUnidadesMutalisco(){
        this.poblacionNecesaria = poblacionQueOcupa;
    }

    public static int obtenerPoblacionNecesaria(){
        return poblacionQueOcupa;
    }

    public Mutalisco crearUnidad() {
        return new Mutalisco();
    }
}
