package edu.fiuba.algo3.modelo.Imperio;

public class Recurso {

    protected int cantidad;

    public Recurso(int cantidadInicial){
        this.cantidad = cantidadInicial;
    }

    public void consumir(int cantidadAConsumir){
        cantidad -= cantidadAConsumir;
    }

    public void depositar(int cantidadADepositar){
        cantidad += cantidadADepositar;
    }

    public boolean tenesCantidadDeRecurso(int cantidadRecurso) {
        return this.cantidad == cantidadRecurso;
    }

    public int obtenerCantidad() {
        return this.cantidad;
    }

    public boolean tienesMasQue(Recurso recurso) {
        return this.cantidad >= recurso.cantidad;
    }
}
