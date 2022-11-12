package edu.fiuba.algo3.modelo.Edificio_Zerg;

import edu.fiuba.algo3.modelo.UnidadesZerg.Zangano;

public class FabricaZangano implements Fabrica{
    public Zangano crearUnidad(){
        return new Zangano();
        // Ver para despues si hay que delegar en Criadero o no
    }
}
