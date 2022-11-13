package edu.fiuba.algo3.modelo.EdificioProtoss;

import edu.fiuba.algo3.modelo.States.EstadoPuertoEstelar;
import edu.fiuba.algo3.modelo.States.EstadoPuertoEstelarEnConstruccion;

public class PuertoEstelar {

    private EstadoPuertoEstelar estado;
    private int turnoParaEstarConstruido = 10;

    public PuertoEstelar() {
        estado = new EstadoPuertoEstelarEnConstruccion(turnoParaEstarConstruido);
    }
    public void pasarTurno() {estado = estado.actualizar();}
    public FabricaScout crearFabricaScout() {return estado.crearFabricaScout();}
}
