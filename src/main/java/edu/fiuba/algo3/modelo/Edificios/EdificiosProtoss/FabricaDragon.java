package edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.UnidadProtoss;

public class FabricaDragon extends Fabrica {

    public FabricaDragon(){
        this.poblacionNecesaria = 3;
    }

    public UnidadProtoss crearUnidad(){
        return new Dragon();
    }
}
