package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;
import edu.fiuba.algo3.modelo.UnidadesZerg.UnidadZerg;

public class EstadoCriaderoConstruido implements EstadoCriadero{
    public UnidadZerg crearUnidad(Fabrica unaFabrica){
        return unaFabrica.crearUnidad();
    }

    public EstadoCriadero actualizar(){
        return this;
    }
}
