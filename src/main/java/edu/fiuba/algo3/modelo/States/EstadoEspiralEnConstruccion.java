package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;
import edu.fiuba.algo3.modelo.EdificioZerg.FabricaMutalisco;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;

import java.util.ArrayList;

public class EstadoEspiralEnConstruccion implements EstadoEspiral {

    private int turnoParaEstarConstruido;

    public EstadoEspiralEnConstruccion(int turnoParaEstarConstruido) {
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    @Override
    public EstadoEspiral actualizar(ArrayList<Fabrica> listaDeFabricasDisponibles) {
        turnoParaEstarConstruido--;
        if (turnoParaEstarConstruido == 0) {
            if (listaDeFabricasDisponibles != null)
                listaDeFabricasDisponibles.add(new FabricaMutalisco());
            return new EstadoEspiralConstruida();
        }
        return this;
    }

    @Override
    public FabricaMutalisco crearFabricaMutalisco() {
        throw new ErrorEdificioNoEstaConstruido();
    }
}
