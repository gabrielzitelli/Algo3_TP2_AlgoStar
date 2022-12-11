package edu.fiuba.algo3.modelo.Edificios.Fabricas;

import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Mutalisco;

public class FabricaMutalisco extends Fabrica {

    private static final int poblacionQueOcupa = 4;

    public FabricaMutalisco(){
        this.poblacionNecesaria = poblacionQueOcupa;
    }

    public static int obtenerPoblacionNecesaria(){
        return poblacionQueOcupa;
    }

    public Mutalisco crearUnidad() {
        return new Mutalisco();
    }
}
