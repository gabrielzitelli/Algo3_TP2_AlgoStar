package edu.fiuba.algo3.modelo.Imperio;

import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;

public class Recurso {

    private int cantidad;

    public Recurso(int cantidadInicial){
        this.cantidad = cantidadInicial;
    }

    public void consumir(int cantidadAConsumir){
        if(cantidad < cantidadAConsumir)
            throw new ErrorCantidadDeRecursoInsuficiente();

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
}
