package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;
import edu.fiuba.algo3.modelo.EdificioZerg.FabricaHidralisco;

import java.util.ArrayList;

public interface EstadoGuarida {

    public abstract EstadoGuarida actualizar(ArrayList<Fabrica> listaDeFabricasDisponibles);
    public abstract FabricaHidralisco crearFabricaHidralisco();
}
