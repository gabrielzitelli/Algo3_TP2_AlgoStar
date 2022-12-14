package edu.fiuba.algo3.modelo.Edificios.Vida;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Excepciones.ErrorVidaLlegoACero;

public class VidaConEscudo implements Vida{

    private int cantidadVida;
    private int cantidadEscudo;
    private final int capacidadEscudo;
    private final int capacidadVida;

    public VidaConEscudo(int cantidadVida, int cantidadEscudo){
        // Se settea la cantidad de vida y esucdo deseada
        this.cantidadVida = cantidadVida;
        this.capacidadVida = cantidadVida;
        this.cantidadEscudo = cantidadEscudo;
        this.capacidadEscudo = cantidadEscudo;
    }

    public void aplicarAtaque(Ataque unAtaque){
        // se aplica el ataque a la vida con escudo. Primero se saca vida del escudo, y si este llega
        // a cero se saca vida del la vida plana. Si la vida plana, luego de aplicar el daño
        // es menor o igual a cero se lanza ErrorVidaLlegoACero
        int cantidadEscudoRestante = unAtaque.aplicarAtaque(this.cantidadEscudo);

        if(cantidadEscudoRestante <= 0){
            this.cantidadVida += cantidadEscudoRestante;
            this.cantidadEscudo = 0;
        }else
            this.cantidadEscudo = cantidadEscudoRestante;

        this.validarVidaLlegoACero();
    }

    public void pasarTurno(){
        // Se regenera el escudo, si con la suma de la regeneracion sobrepasa el maximo
        // inicial, entonces se regenera hasta el tope inicial
        //supuesto
        double porcentajeDeRegeneracion = 0.05;
        int escudoRegenerado = (int)(this.capacidadEscudo * porcentajeDeRegeneracion);

        if ((this.cantidadEscudo + escudoRegenerado) >= this.capacidadEscudo){
            this.cantidadEscudo = this.capacidadEscudo;
            return;
        }

        this.cantidadEscudo += escudoRegenerado;
    }

    private void validarVidaLlegoACero(){
        if(this.cantidadVida <= 0)
            throw new ErrorVidaLlegoACero();
    }

    @Override
    public String toString() {
        return " vidaActual " + this.cantidadVida + " vidaMaxima " + this.capacidadVida + " escudoActual "
                + this.cantidadEscudo + " escudoMaximo " + this.capacidadEscudo;
    }
}
