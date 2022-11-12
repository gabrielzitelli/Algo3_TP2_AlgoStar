package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificio_Zerg.Fabrica;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.UnidadesZerg.UnidadZerg;
import edu.fiuba.algo3.modelo.UnidadesZerg.Zangano;

public class EstadoCriaderoConstruido implements EstadoCriadero{
    public UnidadZerg crearUnidad(Fabrica unaFabrica){
        return unaFabrica.crearUnidad();
    }

    public EstadoCriadero actualizar(){
        return this;
    }
}
