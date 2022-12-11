package edu.fiuba.algo3.modelo.Edificios.Vida;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Excepciones.ErrorVidaLlegoACero;

public class VidaSimple implements Vida{
    private int cantidad;
    private int capacidad;

    public VidaSimple(int cantidad){
        // Se settea la cantidad de vida deseada
        this.cantidad = cantidad;
        this.capacidad = cantidad;

    }

    public void aplicarAtaque(Ataque unAtaque){
        this.cantidad = unAtaque.aplicarAtaque(this.cantidad);
        this.validarVidaLlegoACero();
    }

    public void pasarTurno(){
        this.validarVidaLlegoACero();
    }

    private void validarVidaLlegoACero(){
        if(this.cantidad <= 0)
            throw new ErrorVidaLlegoACero();
    }

    @Override
    public String toString() {
        return " vidaActual " + this.cantidad + " vidaMaxima " + this.capacidad;
    }
}
