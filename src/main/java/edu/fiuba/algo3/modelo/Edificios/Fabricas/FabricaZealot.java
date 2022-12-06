package edu.fiuba.algo3.modelo.Edificios.Fabricas;

import edu.fiuba.algo3.modelo.Edificios.Fabricas.Fabrica;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;

public class FabricaZealot extends Fabrica {

    public FabricaZealot(){
        this.poblacionNecesaria = 2;
    }

    public Zealot crearUnidad(){
        return new Zealot();
    }
}