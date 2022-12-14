package edu.fiuba.algo3.modelo.Edificios.FabricasUnidades;

import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Scout;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.UnidadProtoss;

public class FabricasUnidadesScout extends FabricasUnidades {

    private static final int poblacionQueOcupa = 4;

    public FabricasUnidadesScout(){
        this.poblacionNecesaria = poblacionQueOcupa;
    }

    public static int obtenerPoblacionNecesaria(){
        return poblacionQueOcupa;
    }

    public UnidadProtoss crearUnidad(){
        return new Scout();
    }
}
