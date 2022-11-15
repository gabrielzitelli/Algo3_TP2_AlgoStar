package edu.fiuba.algo3.modelo.danioYAtaque;

public class DanioMutalisco implements DanioUnidad {

    private Danio danioTerrestre;
    private Danio danioAereo;

    public DanioMutalisco(int danioTerrestre, int danioAereo){
        this.danioTerrestre = new Danio(danioTerrestre);
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
