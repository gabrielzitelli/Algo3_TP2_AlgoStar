package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Unidad;

import java.util.ArrayList;

public interface EstadoCriadero {
    public abstract void crearUnidad(Fabrica unaFabrica, ArrayList<Unidad> unidades);
    public abstract EstadoCriadero actualizar(Coordenada coordenada);
}
