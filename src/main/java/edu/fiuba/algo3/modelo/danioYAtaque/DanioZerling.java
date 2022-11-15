package edu.fiuba.algo3.modelo.danioYAtaque;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoPuedeAtacarUnidadVoladora;

public class DanioZerling implements DanioUnidad {

    private Danio danioTerrestre;

    public DanioZerling(int danioTerrestre){
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
