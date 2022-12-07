package edu.fiuba.algo3.modelo.Ataque;

public class Ataque {
    // La clase ataque es la encargada aplicar el daño que se le pasa
    private Danio danio;
    private TipoDanio tipoDanio;

    public Ataque(Danio unDanio){
        this.danio = unDanio;
    }

    public Ataque(TipoDanio unTipoDanio) {
        this.tipoDanio = unTipoDanio;
    }

    public int aplicarAtaque(int cantidadVida) {
        // Retorna el resultado de que Danio aplique su daño
        if (danio == null)
            tipoDanio.danioTerrestre();

        return this.danio.aplicarDanio(cantidadVida);
    }

    public Ataque ataqueTerrestre() {
        return new Ataque(tipoDanio.danioTerrestre());
    }

    public Ataque ataqueAereo() {
        return new Ataque(tipoDanio.danioAereo());
    }
}
