package edu.fiuba.algo3.modelo.vida;

public class VidaRegenerativa implements Vida{
    private int cantidad;
    private int capacidad;
    private double porcentajeDeRegeneracion;

    public VidaRegenerativa(int cantidad, double porcentajeDeRegeneracion){
        this.cantidad = cantidad;
        this.capacidad = cantidad;
        this.porcentajeDeRegeneracion = porcentajeDeRegeneracion;
    }
    @Override
    public int getVida(){
        return this.cantidad;
    }
    @Override
    public void aplicarDanio(int danioHecho){
        this.cantidad -= danioHecho;
    }

    @Override
    public void accionDeTurno(){
        int vidaRegenerada = (int)(this.capacidad * this.porcentajeDeRegeneracion);
        if ((this.cantidad + vidaRegenerada) >= this.capacidad){
            this.cantidad = this.capacidad;
        }else {
            this.cantidad += vidaRegenerada;
        }
    }
}
