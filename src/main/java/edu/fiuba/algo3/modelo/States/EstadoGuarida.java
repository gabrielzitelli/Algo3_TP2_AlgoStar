package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.FabricaHidralisco;

import java.util.ArrayList;

public interface EstadoGuarida {

    public abstract EstadoGuarida actualizar(ArrayList<Fabrica> listaDeFabricasDisponibles);
    public abstract FabricaHidralisco crearFabricaHidralisco();
}
