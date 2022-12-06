package edu.fiuba.algo3.modelo.Edificios.Fabricas;

import edu.fiuba.algo3.modelo.Edificios.Fabricas.Fabrica;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Scout;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.UnidadProtoss;

public class FabricaScout extends Fabrica {

    public FabricaScout(){
        this.poblacionNecesaria = 4;
    }

    public UnidadProtoss crearUnidad(){
        return new Scout();
    }
}
