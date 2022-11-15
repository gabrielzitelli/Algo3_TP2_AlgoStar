package edu.fiuba.algo3.modelo.danioYAtaque;

public class Ataque {
    // La clase ataque es la encargada aplicar el daño que se le pasa
    private Danio danio;

    public Ataque(Danio unDanio){
        this.danio = unDanio;
    }

    public int aplicarAtaque(int cantidadVida) {
        // Retorna el resultado de que Danio aplique su daño
        return this.danio.aplicarDanio(cantidadVida);
    }
}
