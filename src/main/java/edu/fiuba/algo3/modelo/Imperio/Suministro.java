package edu.fiuba.algo3.modelo.Imperio;

import static java.lang.Math.min;

public class Suministro{

    private final int cantidadLimite = 200;
    private int cantidad;
    private int poblacion = 0 ;


    public Suministro(int cantidadInicial) {
        cantidad = cantidadInicial;
    }

    public void aumentarSuministro(int cantidadADepositar){
        cantidad += cantidadADepositar;
    }

    public void disminuirSuministro(int cantidadAConsumir){

        if ((cantidad-cantidadAConsumir) < 0){
            cantidad = 0;
        }
        else{
            cantidad -= cantidadAConsumir;
        }

    }

    public int obtenerSuministro() {
        return min(cantidadLimite, cantidad);
    }


}