package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioProtoss.FabricaDragon;
import edu.fiuba.algo3.modelo.EdificioProtoss.FabricaZealot;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;

public class EstadoAccesoEnConstruccion implements EstadoAcceso {

    private int turnoParaEstarConstruido;
    public EstadoAccesoEnConstruccion(int turnoParaEstarConstruido) {
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    public FabricaDragon crearFabricaDragon(){
        throw new ErrorEdificioNoEstaConstruido();
    }

    public FabricaZealot crearFabricaZealot(){
        throw new ErrorEdificioNoEstaConstruido();
    }

    public EstadoAcceso actualizar(){
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0)
            return new EstadoAccesoConstruido();

        return this;
    }
}
