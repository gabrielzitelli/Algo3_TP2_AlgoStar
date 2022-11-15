package edu.fiuba.algo3.modelo.danioYAtaque;

public class DanioZealot implements Danio{
    int danio;

    public DanioZealot(int danio){
        this.danio = danio;
    }
    public int aplicarDanio(int cantidadVida){
        // Devuelve la cantidad de vida luego de aplicarle el daño
        return cantidadVida - danio;
    }
}
