package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioProtoss.FabricaScout;
import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;

import java.util.ArrayList;

public interface EstadoPuertoEstelar {
    EstadoPuertoEstelar actualizar(ArrayList<Fabrica> listaDeFabricasDisponibles);

    FabricaScout crearFabricaScout();
}
