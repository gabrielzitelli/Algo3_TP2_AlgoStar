package edu.fiuba.algo3.modelo.Edificios.Fabricas;

import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;

public class FabricaZerling extends Fabrica {

    private static final int poblacionQueOcupa = 1;

    public FabricaZerling(){
        this.poblacionNecesaria = poblacionQueOcupa;
    }

    public static int obtenerPoblacionNecesaria(){
        return poblacionQueOcupa;
    }

    public Zerling crearUnidad(){
        return new Zerling();
    }
}
