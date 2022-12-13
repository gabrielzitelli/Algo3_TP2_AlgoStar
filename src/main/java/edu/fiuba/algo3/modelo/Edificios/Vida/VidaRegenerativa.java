package edu.fiuba.algo3.modelo.Edificios.Vida;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Excepciones.ErrorVidaLlegoACero;

public class VidaRegenerativa implements Vida{
    private int cantidad;
    private final int capacidad;


    public VidaRegenerativa(int cantidad){
        // Se settea la cantidad de vida deseada
        this.cantidad = cantidad;
        this.capacidad = cantidad;
    }

    public void aplicarAtaque(Ataque unAtaque){
        // se aplica el ataque a la vida. Si la vida, luego de aplicar el daño
        // es menor o igual a cero se lanza ErrorVidaLlegoACero
        this.cantidad = unAtaque.aplicarAtaque(this.cantidad);
        this.validarVidaLlegoACero();
    }

    public void pasarTurno(){
        // Se regenera la vida, si con la suma de la regeneracion sobrepasa el maximo
        // inicial, entonces se regenera hasta el tope inicial
        this.validarVidaLlegoACero();

        //supuesto
        double porcentajeDeRegeneracion = 0.15;
        int cantidadARegenerar = (int)(this.capacidad * porcentajeDeRegeneracion);

        if((cantidad + cantidadARegenerar) >= this.capacidad)
            this.cantidad = this.capacidad;
        else
            this.cantidad += cantidadARegenerar;
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