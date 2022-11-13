package edu.fiuba.algo3.modelo.danioYAtaque;

public class Ataque {

    private DanioBasico danio;

    public Ataque(DanioBasico unDanio){
        this.danio = unDanio;
    }

    public int aplicarAtaque(int cantidadVida) {
        return this.danio.aplicarDanio(cantidadVida);
    }
}
