package edu.fiuba.algo3.modelo.Ataque;

public class Ataque {
    // La clase ataque es la encargada aplicar el daño que se le pasa
    private Danio danio;
    private final TipoDanio tipoDanio;

    public Ataque(TipoDanio unTipoDanio) {
        this.tipoDanio = unTipoDanio;
    }

    public int aplicarAtaque(int cantidadVida) {
        if (danio == null)
            danio = tipoDanio.danioTerrestre();

        return this.danio.aplicarDanio(cantidadVida);
    }

    public Ataque ataqueTerrestre() {
        danio = tipoDanio.danioTerrestre();
        return this;
    }

    public Ataque ataqueAereo() {
        danio = tipoDanio.danioAereo();
        return this;
    }
}
