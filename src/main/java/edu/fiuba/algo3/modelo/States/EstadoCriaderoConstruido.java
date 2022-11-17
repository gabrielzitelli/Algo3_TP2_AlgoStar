package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Moho;
import edu.fiuba.algo3.modelo.Edificios.GestorDeCrianza;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;

public class EstadoCriaderoConstruido implements EstadoCriadero{

    Moho moho = new Moho();
    GestorDeCrianza gestorDeCrianza = new GestorDeCrianza();

    public EstadoCriaderoConstruido(Coordenada coordenada) {
        moho.expandir(coordenada);
    }

    public void crearUnidad(Fabrica unaFabrica, ArrayList<Unidad> unidades){
        gestorDeCrianza.agregarUnidad(unaFabrica.crearUnidad(), unidades);
    }


    public EstadoCriadero actualizar(Coordenada coordenada) {
        moho.expandir(coordenada);
        gestorDeCrianza.actualizar();
        return this;
    }

}
