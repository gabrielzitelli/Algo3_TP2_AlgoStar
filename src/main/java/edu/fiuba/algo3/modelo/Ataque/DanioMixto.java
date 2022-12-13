package edu.fiuba.algo3.modelo.Ataque;

public class DanioMixto implements TipoDanio {

    private final Danio danioTerrestre;
    private final Danio danioAereo;

    public DanioMixto(int danioTerretre, int danioAereo) {
        this.danioTerrestre = new Danio(danioTerretre);
        this.danioAereo = new Danio(danioAereo);
    }

    @Override
    public Danio danioTerrestre() {
        return danioTerrestre;
    }

    @Override
    public Danio danioAereo() {
        return danioAereo;
    }
}
