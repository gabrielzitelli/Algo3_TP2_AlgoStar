package edu.fiuba.algo3.modelo.vida;

public class VidaConEscudo implements Vida{

    private int vidaPlana;
    private double porcentajeDeRegeneracion;
    private int cantidadEscudo;
    private int capacidadEscudo;

    public VidaConEscudo(int vida, double porcentajeDeRegeneracion, int escudo){
        this.vidaPlana = vida;
        this.porcentajeDeRegeneracion = porcentajeDeRegeneracion;
        this.cantidadEscudo = escudo;
        this.capacidadEscudo = escudo;
    }

    @Override
    public int getVida(){
        return (this.vidaPlana + this.cantidadEscudo);
    }

    @Override
    public void aplicarDanio(int danioHecho){
        this.cantidadEscudo -= danioHecho;
    }

    @Override
    public void accionDeTurno(){
        int escudoRegenerado = (int)(this.capacidadEscudo * this.porcentajeDeRegeneracion);
        if ((this.cantidadEscudo + escudoRegenerado) >= this.capacidadEscudo){
            this.cantidadEscudo = this.capacidadEscudo;
        }else {
            this.cantidadEscudo += escudoRegenerado;
        }
    }

}

