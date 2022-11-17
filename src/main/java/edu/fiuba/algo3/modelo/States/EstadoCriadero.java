package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;

public interface EstadoCriadero {

    public abstract void crearUnidad(Fabrica unaFabrica, ArrayList<Unidad> unidades);
    public abstract EstadoCriadero actualizar(Coordenada coordenada);
}
