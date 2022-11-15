package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;
import edu.fiuba.algo3.modelo.EdificioZerg.Moho;
import edu.fiuba.algo3.modelo.GestorDeCrianza;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Unidad;

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

    @Override
    public EstadoCriadero actualizar(Coordenada coordenada) {
        moho.expandir(coordenada);
        gestorDeCrianza.actualizar();
        return this;
    }

}
