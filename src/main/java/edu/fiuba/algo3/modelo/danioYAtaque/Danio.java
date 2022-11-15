package edu.fiuba.algo3.modelo.danioYAtaque;

public class Danio {

    private int danio;

    public Danio(int danio){
        this.danio = danio;
    }

    public int aplicarDanio(int cantidadVida){
        // Devuelve la cantidad de vida luego de aplicarle el daño
        return cantidadVida - danio;
    }
}
