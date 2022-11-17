package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.FabricaScout;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;

import java.util.ArrayList;

public class EstadoPuertoEstelarConstruida implements EstadoPuertoEstelar {

    public EstadoPuertoEstelar actualizar(ArrayList<Fabrica> listaDeFabricasDisponibles)  {return this;}

    public FabricaScout crearFabricaScout() {
        return new FabricaScout();
    }
}
