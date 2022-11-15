package edu.fiuba.algo3.modelo.EdificioProtoss;

import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;
import edu.fiuba.algo3.modelo.UnidadesProtoss.Zealot;

public class FabricaZealot extends Fabrica {
    public Zealot crearUnidad(){
        return new Zealot();
    }
}
