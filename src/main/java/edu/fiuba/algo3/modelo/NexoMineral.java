package edu.fiuba.algo3.modelo;

public class NexoMineral extends Edificio {

    private Recurso minerales;

    public NexoMineral(NodoCompatible requisitos, Recurso _minerales) {
        this.nodoCompatible = requisitos;
        this.minerales = _minerales;
    }

    @Override
    public void accionDeTurno() {
        // TODO
    }
}
