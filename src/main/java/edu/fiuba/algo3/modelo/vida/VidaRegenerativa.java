package edu.fiuba.algo3.modelo.vida;

import edu.fiuba.algo3.modelo.Excepciones.ErrorVidaLlegoACero;
import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;

public class VidaRegenerativa implements Vida{
    private int cantidad;
    private int capacidad;
    private double porcentajeDeRegeneracion = 0.15;


    public VidaRegenerativa(int cantidad){
        this.cantidad = cantidad;
        this.capacidad = cantidad;
    }

    public void aplicarAtaque(Ataque unAtaque){
        this.cantidad = unAtaque.aplicarAtaque(this.cantidad);
        this.validarVidaLlegoACero();
    }

    public void accionDeTurno(){
        this.validarVidaLlegoACero();

        int cantidadARegenerar = (int)(this.capacidad * this.porcentajeDeRegeneracion);
        if((cantidad + cantidadARegenerar) >= this.capacidad){
            this.cantidad = this.capacidad;
        }else{
            this.cantidad += cantidadARegenerar;
        }
    }

    private void validarVidaLlegoACero(){
        if(this.cantidad <= 0){
            throw new ErrorVidaLlegoACero();
        }
    }
}