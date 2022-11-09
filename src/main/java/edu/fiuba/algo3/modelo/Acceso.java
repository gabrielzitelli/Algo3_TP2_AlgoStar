package edu.fiuba.algo3.modelo;

public class Acceso extends Edificio {

    public Acceso(NodoCompatible requisitos) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 8;
        this.nodoCompatible = requisitos;
    }

    @Override
    public void accionDeTurno() {
        turnosExistiendo ++;
        // TODO
    }
}
