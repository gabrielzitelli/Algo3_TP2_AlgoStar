package edu.fiuba.algo3.modelo.Imperio;

import edu.fiuba.algo3.modelo.Excepciones.ErrorCriaderoNoTieneMasLarvas;
import edu.fiuba.algo3.modelo.Excepciones.ErrorSuperaMaximoDePoblacionActual;

import static java.lang.Math.min;

public class Suministro{

    private final int cantidadLimite = 200;
    private int cantidad;
    private int poblacion ;


    public Suministro(int cantidadInicial) {
        cantidad = cantidadInicial;
        poblacion = 0;
    }

    public void aumentarSuministro(int cantidadADepositar){
        cantidad += cantidadADepositar;
    }

    public void disminuirSuministro(int cantidadAConsumir){

        if ((cantidad-cantidadAConsumir) < 0)
            cantidad = 0;
        else
            cantidad -= cantidadAConsumir;
    }

    public int obtenerSuministro() {
        return min(cantidadLimite, cantidad);
    }

    public int obtenerPoblacion(){
        return poblacion;
    }

    public void tenesCapacidad(int poblacionNecesaria) {
        if(this.poblacion + poblacionNecesaria > obtenerSuministro())
            throw new ErrorSuperaMaximoDePoblacionActual();
    }

    public void agregarPoblacion(int poblacionNecesaria) {
        this.poblacion += poblacionNecesaria;
    }

    public void disminuirPoblacion(int cantidadADisminuir){

        if ((poblacion-cantidadADisminuir) < 0)
            poblacion = 0;
        else
            poblacion -= cantidadADisminuir;
    }
}