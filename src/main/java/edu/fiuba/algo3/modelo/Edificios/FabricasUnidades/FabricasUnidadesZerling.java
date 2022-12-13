package edu.fiuba.algo3.modelo.Edificios.FabricasUnidades;

import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;

public class FabricasUnidadesZerling extends FabricasUnidades {

    private static final int poblacionQueOcupa = 1;

    public FabricasUnidadesZerling(){
        this.poblacionNecesaria = poblacionQueOcupa;
    }

    public static int obtenerPoblacionNecesaria(){
        return poblacionQueOcupa;
    }

    public Zerling crearUnidad(){
        return new Zerling();
    }
}
