package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.FabricaDragon;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.FabricaZealot;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;

public interface EstadoAcceso {

    public abstract EstadoAcceso actualizar();
    public abstract FabricaDragon crearFabricaDragon();
    public abstract FabricaZealot crearFabricaZealot();
    public abstract void crearUnidad(Fabrica unaFabrica, ArrayList<Unidad> unidades);
}
