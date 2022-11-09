package edu.fiuba.algo3.modelo;

public class Espiral extends Edificio {

    public Espiral(NodoCompatible requisitos) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 10;
        this.nodoCompatible = requisitos;
    }

    @Override
    public void accionDeTurno() {
        turnosExistiendo ++;
        // TODO
    }
}
