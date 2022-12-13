package edu.fiuba.algo3.modelo.Edificios.FabricasUnidades;

import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.UnidadProtoss;

public class FabricasUnidadesDragon extends FabricasUnidades {

    private static final int poblacionQueOcupa = 3;

    public FabricasUnidadesDragon(){
        this.poblacionNecesaria = poblacionQueOcupa;
    }

    public static int obtenerPoblacionNecesaria(){
        return poblacionQueOcupa;
    }

    public UnidadProtoss crearUnidad(){
        return new Dragon();
    }
}
