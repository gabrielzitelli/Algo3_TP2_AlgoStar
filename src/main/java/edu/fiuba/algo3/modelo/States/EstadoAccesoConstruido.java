package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.FabricaDragon;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.FabricaZealot;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.GestorDeCrianza;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

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

    public void crearUnidad(Fabrica unaFabrica, ArrayList<Unidad> unidades) {
        gestorDeCrianza.agregarUnidad(unaFabrica.crearUnidad(), unidades);
    }

    public FabricaDragon crearFabricaDragon(){
        return new FabricaDragon();
    }
}
