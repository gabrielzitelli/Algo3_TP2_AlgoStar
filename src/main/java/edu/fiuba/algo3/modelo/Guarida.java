package edu.fiuba.algo3.modelo;

public class Guarida extends Edificio {

    public Guarida(NodoCompatible requisitos) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 12;
        this.nodoCompatible = requisitos;
    }

    @Override
    public void accionDeTurno() {
        turnosExistiendo ++;
        // TODO
    }
}
