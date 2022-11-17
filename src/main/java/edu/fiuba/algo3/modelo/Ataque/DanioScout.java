package edu.fiuba.algo3.modelo.Ataque;

public class DanioScout implements DanioUnidad {

    private Danio danioTerrestre;
    private Danio danioAereo;

    public DanioScout(int danioTerrestre, int danioAereo) {
        this.danioTerrestre = new Danio(danioTerrestre);
        this.danioAereo = new Danio(danioAereo);
    }

    public Danio danioTerrestre() {
        return danioTerrestre;
    }

    public Danio danioAereo() {
        return danioAereo;
    }
}
