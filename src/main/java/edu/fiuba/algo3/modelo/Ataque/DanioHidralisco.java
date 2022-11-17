package edu.fiuba.algo3.modelo.Ataque;

public class DanioHidralisco implements DanioUnidad{

    private Danio danioTerrestre;
    private Danio danioAereo;

    public DanioHidralisco(int danioTerrestre, int danioAereo){
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
