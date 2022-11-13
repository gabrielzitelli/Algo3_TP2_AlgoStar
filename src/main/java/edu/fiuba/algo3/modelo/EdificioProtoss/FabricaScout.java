package edu.fiuba.algo3.modelo.EdificioProtoss;

import edu.fiuba.algo3.modelo.UnidadesProtoss.Scout;
import edu.fiuba.algo3.modelo.UnidadesProtoss.UnidadProtoss;

public class FabricaScout {
    public UnidadProtoss crearUnidad(){
        return new Scout();
    }
}
