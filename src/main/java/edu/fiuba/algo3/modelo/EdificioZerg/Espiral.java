package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.States.EstadoEspiral;
import edu.fiuba.algo3.modelo.States.EstadoEspiralEnConstruccion;

public class Espiral {

    private EstadoEspiral estado;

    private int turnoParaEstarConstruido = 10;

    public Espiral() {
        estado = new EstadoEspiralEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno() {
        estado = estado.actualizar();
    }

    public FabricaMutalisco crearFabricaMutalisco() {
        return estado.crearFabricaMutalisco();
    }
}
