package edu.fiuba.algo3.modelo.Edificios.FabricasUnidades;

import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;

public class FabricaZealot extends Fabrica {

    private static int poblacionQueOcupa = 2;

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
