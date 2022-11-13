package edu.fiuba.algo3.modelo.EdificioProtoss;

import edu.fiuba.algo3.modelo.UnidadesProtoss.UnidadProtoss;
import edu.fiuba.algo3.modelo.UnidadesProtoss.Zealot;

public class FabricaZealot {
    public UnidadProtoss crearUnidad(){
        return new Zealot();
    }
}
