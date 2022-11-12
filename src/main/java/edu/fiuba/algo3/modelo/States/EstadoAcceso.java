package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificio_Protoss.FabricaDragon;
import edu.fiuba.algo3.modelo.Edificio_Protoss.FabricaZealot;
import edu.fiuba.algo3.modelo.Edificio_Zerg.Fabrica;
import edu.fiuba.algo3.modelo.UnidadeProtoss.UnidadProtoss;
import edu.fiuba.algo3.modelo.UnidadesZerg.UnidadZerg;

public interface EstadoAcceso {

    public abstract EstadoAcceso actualizar();
    public abstract FabricaDragon crearFabricaDragon();
    public abstract FabricaZealot crearFabricaZealot();
}
