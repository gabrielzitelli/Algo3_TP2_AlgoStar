package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Mutalisco;

public class FabricaMutalisco extends Fabrica {

    public FabricaMutalisco(){
        this.poblacionNecesaria = 4;
    }

    public Mutalisco crearUnidad() {
        return new Mutalisco();
    }
}
