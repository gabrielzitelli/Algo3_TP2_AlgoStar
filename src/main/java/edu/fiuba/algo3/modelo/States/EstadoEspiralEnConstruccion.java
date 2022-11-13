package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificio_Zerg.FabricaMutalisco;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;

public class EstadoEspiralEnConstruccion implements EstadoEspiral {

    private int turnoParaEstarConstruido;

    public EstadoEspiralEnConstruccion(int turnoParaEstarConstruido) {
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    @Override
    public EstadoEspiral actualizar() {
        turnoParaEstarConstruido--;
        if (turnoParaEstarConstruido == 0) {
            return new EstadoEspiralConstruida();
        }
        return this;
    }

    @Override
    public FabricaMutalisco crearFabricaMutalisco() {
        throw new ErrorEdificioNoEstaConstruido();
    }
}
