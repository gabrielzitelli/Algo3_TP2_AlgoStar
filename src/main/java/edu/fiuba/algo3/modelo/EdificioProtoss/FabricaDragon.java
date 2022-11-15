package edu.fiuba.algo3.modelo.EdificioProtoss;

import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;
import edu.fiuba.algo3.modelo.UnidadesProtoss.Dragon;
import edu.fiuba.algo3.modelo.UnidadesProtoss.UnidadProtoss;

public class FabricaDragon extends Fabrica {
    public UnidadProtoss crearUnidad(){
        return new Dragon();
    }
}
