package edu.fiuba.algo3.modelo.Edificios.FabricasUnidades;

import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.UnidadProtoss;

public class FabricaDragon extends Fabrica {

    private static int poblacionQueOcupa = 3;

    public FabricaDragon(){
        this.poblacionNecesaria = poblacionQueOcupa;
    }

    public static int obtenerPoblacionNecesaria(){
        return poblacionQueOcupa;
    }

    public UnidadProtoss crearUnidad(){
        return new Dragon();
    }
}
