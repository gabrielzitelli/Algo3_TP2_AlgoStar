package edu.fiuba.algo3.modelo.danioYAtaque;

public class DanioBasico {

    int danio;
    public DanioBasico(int danio){
        this.danio = danio;
    }
    public int aplicarDanio(int cantidadVida){
        return cantidadVida - danio;
    }
}
