package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioProtoss.FabricaDragon;
import edu.fiuba.algo3.modelo.EdificioProtoss.FabricaZealot;
import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;
import edu.fiuba.algo3.modelo.GestorDeCrianza;
import edu.fiuba.algo3.modelo.Unidad;

import java.util.ArrayList;

public class EstadoAccesoConstruido implements EstadoAcceso {
    GestorDeCrianza gestorDeCrianza = new GestorDeCrianza();
    public EstadoAcceso actualizar(){
        gestorDeCrianza.actualizar();
        return this;
    }

    public FabricaZealot crearFabricaZealot(){
        return new FabricaZealot();
    }
    @Override
    public void crearUnidad(Fabrica unaFabrica, ArrayList<Unidad> unidades) {
        gestorDeCrianza.agregarUnidad(unaFabrica.crearUnidad(), unidades);
    }

    public FabricaDragon crearFabricaDragon(){
        return new FabricaDragon();
    }
}
