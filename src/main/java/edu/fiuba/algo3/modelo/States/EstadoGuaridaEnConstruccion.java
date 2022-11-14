package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioZerg.FabricaHidralisco;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;

public class EstadoGuaridaEnConstruccion implements EstadoGuarida {

    private int turnoParaEstarConstruido;

    public EstadoGuaridaEnConstruccion(int turnoParaEstarConstruido) {
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    public FabricaHidralisco crearFabricaHidralisco(){
        throw new ErrorEdificioNoEstaConstruido();
    }

    public EstadoGuarida actualizar(){
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0)
            return new EstadoGuaridaConstruida();

        return this;
    }
}
