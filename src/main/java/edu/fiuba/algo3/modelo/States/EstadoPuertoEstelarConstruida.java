package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioProtoss.FabricaScout;
import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;

import java.util.ArrayList;

public class EstadoPuertoEstelarConstruida implements EstadoPuertoEstelar {
    @Override
    public EstadoPuertoEstelar actualizar(ArrayList<Fabrica> listaDeFabricasDisponibles)  {return this;}

    @Override
    public FabricaScout crearFabricaScout() {
        return new FabricaScout();
    }
}
