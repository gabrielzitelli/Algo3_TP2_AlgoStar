package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.CantidadDeUtilidadInsuficiente;

public class Recurso {
    private int cantidad;

    public Recurso(){
        cantidad = 0;
    }

    public Recurso(int cantidadInicial){
        cantidad = cantidadInicial;
    }

    public void consumir(int cantidadAConsumir){
        if(cantidad < cantidadAConsumir)
            throw new CantidadDeUtilidadInsuficiente();

        cantidad -= cantidadAConsumir;
    }

    public void depositar(int cantidadADepositar){
        cantidad += cantidadADepositar;
    }
}
