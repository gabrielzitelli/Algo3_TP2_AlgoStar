package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioProtoss.FabricaDragon;
import edu.fiuba.algo3.modelo.EdificioProtoss.FabricaZealot;
import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;
import edu.fiuba.algo3.modelo.Unidad;

import java.util.ArrayList;

public interface EstadoAcceso {

    public abstract EstadoAcceso actualizar();
    public abstract FabricaDragon crearFabricaDragon();
    public abstract FabricaZealot crearFabricaZealot();

    void crearUnidad(Fabrica unaFabrica, ArrayList<Unidad> unidades);
}
