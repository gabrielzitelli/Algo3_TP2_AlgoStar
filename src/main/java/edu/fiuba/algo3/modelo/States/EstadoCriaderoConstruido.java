package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;
import edu.fiuba.algo3.modelo.EdificioZerg.Moho;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.UnidadesZerg.UnidadZerg;

public class EstadoCriaderoConstruido implements EstadoCriadero{
    Moho moho = new Moho();

    public EstadoCriaderoConstruido(Coordenada coordenada) {
        moho.expandir(coordenada);
    }

    public UnidadZerg crearUnidad(Fabrica unaFabrica){
        return unaFabrica.crearUnidad();
    }

    @Override
    public EstadoCriadero actualizar(Coordenada coordenada) {
        moho.expandir(coordenada);
        return this;
    }

}
