package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.States.EstadoGuarida;
import edu.fiuba.algo3.modelo.States.EstadoGuaridaEnConstruccion;

public class Guarida {

    private EstadoGuarida estado;

    private int turnoParaEstarConstruido = 12;

    public Guarida(){
        //Aplicacion de patron State
        estado = new EstadoGuaridaEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno() {
        estado = estado.actualizar();
    }

    public FabricaHidralisco crearFabricaHidralisco() {
        return estado.crearFabricaHidralisco();
    }
}
