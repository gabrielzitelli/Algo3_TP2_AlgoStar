package edu.fiuba.algo3.modelo;


public class Asimilador extends Edificio {

    private Recurso gasVespeno;

    public Asimilador(NodoCompatible requisitos, Recurso _gasVespeno) {
        this.nodoCompatible = requisitos;
        this.gasVespeno = _gasVespeno;
    }

    @Override
    public void accionDeTurno() {
        // TODO
    }
}
