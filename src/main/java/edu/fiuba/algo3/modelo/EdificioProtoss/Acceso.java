package edu.fiuba.algo3.modelo.EdificioProtoss;

import edu.fiuba.algo3.modelo.States.EstadoAcceso;
import edu.fiuba.algo3.modelo.States.EstadoAccesoEnConstruccion;

public class Acceso {

    private int turnoParaEstarConstruido = 8;

    private EstadoAcceso estado;

    public Acceso(){
        estado = new EstadoAccesoEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno(){
        estado = estado.actualizar();
    }

    public FabricaDragon crearFabricaDragon() {
        return estado.crearFabricaDragon();
    }

    public FabricaZealot crearFabricaZealot(){
        return estado.crearFabricaZealot();
    }
}
