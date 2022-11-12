package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificio_Zerg.Fabrica;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.UnidadesZerg.UnidadZerg;

public class EstadoCriaderoEnConstruccion implements EstadoCriadero{

    private int turnoParaEstarConstruido;

    public EstadoCriaderoEnConstruccion(int turnoParaEstarConstruido){
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    public UnidadZerg crearUnidad(Fabrica unaFabrica){
        throw new ErrorEdificioNoEstaConstruido();
    }

    public EstadoCriadero actualizar(){
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0)
            return new EstadoCriaderoConstruido();

        return this;
    }
}
