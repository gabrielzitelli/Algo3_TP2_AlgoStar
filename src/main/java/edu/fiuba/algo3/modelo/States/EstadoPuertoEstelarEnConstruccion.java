package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificio_Protoss.FabricaScout;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;

public class EstadoPuertoEstelarEnConstruccion implements EstadoPuertoEstelar {
    private int turnoParaEstarConstruido;
    public EstadoPuertoEstelarEnConstruccion(int turnoParaEstarConstruido) {
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    @Override
    public EstadoPuertoEstelar actualizar() {
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0)
            return new EstadoPuertoEstelarConstruida();
        return this;
    }

    @Override
    public FabricaScout crearFabricaScout() {
        throw new ErrorEdificioNoEstaConstruido();
    }
}
