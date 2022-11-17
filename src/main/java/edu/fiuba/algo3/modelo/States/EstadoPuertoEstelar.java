package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.FabricaScout;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;

import java.util.ArrayList;

public interface EstadoPuertoEstelar {

    public abstract EstadoPuertoEstelar actualizar(ArrayList<Fabrica> listaDeFabricasDisponibles);
    public abstract FabricaScout crearFabricaScout();
}
