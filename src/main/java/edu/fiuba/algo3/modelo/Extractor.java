package edu.fiuba.algo3.modelo;

public class Extractor extends Edificio {

    private Recurso gasVespeno;

    public Extractor(NodoCompatible requisitos, Recurso _gasVespeno) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 6;
        this.nodoCompatible = requisitos;
        this.gasVespeno = _gasVespeno;
    }

    @Override
    public void accionDeTurno() {
        turnosExistiendo ++;
        // TODO
    }
}
