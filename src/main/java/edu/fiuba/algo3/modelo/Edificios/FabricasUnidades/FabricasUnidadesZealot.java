package edu.fiuba.algo3.modelo.Edificios.FabricasUnidades;

import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;

public class FabricasUnidadesZealot extends FabricasUnidades {

    private static final int poblacionQueOcupa = 2;

    public FabricasUnidadesZealot(){
        this.poblacionNecesaria = poblacionQueOcupa;
    }

    public static int obtenerPoblacionNecesaria(){
        return poblacionQueOcupa;
    }

    public Zealot crearUnidad(){
        return new Zealot();
    }
}
