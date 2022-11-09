package edu.fiuba.algo3.modelo;

public class Extractor extends Edificio {

    private Recurso gasVespeno;

    public Extractor(NodoCompatible requisitos, Recurso _gasVespeno) {
        this.nodoCompatible = requisitos;
        this.gasVespeno = _gasVespeno;
    }

    @Override
    public void accionDeTurno() {
        // TODO
    }
}
