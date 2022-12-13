package edu.fiuba.algo3.modelo.Edificios.Fabricas;

import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;

public class FabricaZealot extends Fabrica {

    private static final int poblacionQueOcupa = 2;

    public FabricaZealot(){
        this.poblacionNecesaria = poblacionQueOcupa;
    }

    public static int obtenerPoblacionNecesaria(){
        return poblacionQueOcupa;
    }

    public Zealot crearUnidad(){
        return new Zealot();
    }
}
