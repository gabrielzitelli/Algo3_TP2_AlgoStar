package edu.fiuba.algo3.modelo.danioYAtaque;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoPuedeAtacarUnidadVoladora;

public class DanioZealot implements DanioUnidad {

    private Danio danioTerrestre;

    public DanioZealot(int danioTerrestre){
        this.danioTerrestre = new Danio(danioTerrestre);
    }

    @Override
    public Danio danioTerrestre() {
        return danioTerrestre;
    }

    @Override
    public Danio danioAereo() {
        throw new ErrorNoPuedeAtacarUnidadVoladora();
    }
}
