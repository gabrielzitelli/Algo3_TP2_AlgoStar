package edu.fiuba.algo3.modelo.Edificios.FabricasUnidades;

import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;

public class FabricaZerling extends Fabrica {

    private static int poblacionQueOcupa = 1;

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
