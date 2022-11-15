package edu.fiuba.algo3.modelo.danioYAtaque;

public class DanioMutalisco implements Danio {
    int danio;

    public DanioMutalisco(int danio){
        this.danio = danio;
    }
    public int aplicarDanio(int cantidadVida){
        // Devuelve la cantidad de vida luego de aplicarle el daño
        return cantidadVida - danio;
    }
}
