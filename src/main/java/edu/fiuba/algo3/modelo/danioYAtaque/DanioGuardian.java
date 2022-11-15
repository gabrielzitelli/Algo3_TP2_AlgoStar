package edu.fiuba.algo3.modelo.danioYAtaque;

public class DanioGuardian implements Danio{

    int danio;

    public DanioGuardian(int danio){
        this.danio = danio;
    }
    public int aplicarDanio(int cantidadVida){
        // Devuelve la cantidad de vida luego de aplicarle el daño
        return cantidadVida - danio;
    }
}
