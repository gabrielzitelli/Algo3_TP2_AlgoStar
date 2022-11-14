package edu.fiuba.algo3.modelo.vida;

import edu.fiuba.algo3.modelo.Excepciones.ErrorVidaLlegoACero;
import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;

public class VidaConEscudo implements Vida{

    private int cantidadVida;
    private double porcentajeDeRegeneracion = 0.15; //supuesto
    private int cantidadEscudo;
    private int capacidadEscudo;

    public VidaConEscudo(int cantidadVida, int cantidadEscudo){
        this.cantidadVida = cantidadVida;
        this.cantidadEscudo = cantidadEscudo;
        this.capacidadEscudo = cantidadEscudo;
    }

    public void aplicarAtaque(Ataque unAtaque){
        int cantidadEscudoRestante = unAtaque.aplicarAtaque(this.cantidadEscudo);
        if(cantidadEscudoRestante <= 0){
            this.cantidadVida += cantidadEscudoRestante;
            this.cantidadEscudo = 0;
        }else {
            this.cantidadEscudo -= cantidadEscudoRestante;
        }
        this.validarVidaLlegoACero();
    }

    public void pasarTurno(){
        int escudoRegenerado = (int)(this.capacidadEscudo * this.porcentajeDeRegeneracion);
        if ((this.cantidadEscudo + escudoRegenerado) >= this.capacidadEscudo){
            this.cantidadEscudo = this.capacidadEscudo;
            return;
        }
        this.cantidadEscudo += escudoRegenerado;
    }

    private void validarVidaLlegoACero(){
        if(this.cantidadVida <= 0){
            throw new ErrorVidaLlegoACero();
        }
    }
}
