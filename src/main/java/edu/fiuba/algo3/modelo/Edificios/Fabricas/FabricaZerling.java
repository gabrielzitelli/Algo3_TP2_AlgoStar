package edu.fiuba.algo3.modelo.Edificios.Fabricas;

import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;

public class FabricaZerling extends Fabrica {

    public FabricaZerling(){
        this.poblacionNecesaria = 1;
    }
    public Zerling crearUnidad(){
        return new Zerling();
    }
}
