package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;

public class FabricaZangano extends Fabrica {

    public Zangano crearUnidad(){
        return new Zangano();
        // Ver para despues si hay que delegar en Criadero o no
    }
}
