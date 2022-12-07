package edu.fiuba.algo3.modelo.Ataque;

public class Danio {

    private int danio;

    public Danio(int danio){
        this.danio = danio;
    }

    public int aplicarDanio(int cantidadVida){
        return cantidadVida - danio;
    }
}
