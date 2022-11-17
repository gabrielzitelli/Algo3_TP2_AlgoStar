package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.FabricaMutalisco;

import java.util.ArrayList;

public interface EstadoEspiral {

    public abstract EstadoEspiral actualizar(ArrayList<Fabrica> listaDeFabricasDisponibles);
    public abstract FabricaMutalisco crearFabricaMutalisco();
}
