package edu.fiuba.algo3.modelo;

public class ReservaDeReproduccion extends Edificio {

    public ReservaDeReproduccion(NodoCompatible requisitos) {
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
