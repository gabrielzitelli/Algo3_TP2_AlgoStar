package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Mutalisco;

public class FabricaMutalisco extends Fabrica {

    public Mutalisco crearUnidad() {
        return new Mutalisco();
    }
}
