package edu.fiuba.algo3.modelo.Ataque;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoPuedeAtacarUnidadVoladora;

public class DanioZerling implements DanioUnidad {

    private Danio danioTerrestre;

    public DanioZerling(int danioTerrestre){
        this.danioTerrestre = new Danio(danioTerrestre);
    }

    public Danio danioTerrestre() {
        return danioTerrestre;
    }

    public Danio danioAereo() {
        throw new ErrorNoPuedeAtacarUnidadVoladora();
    }
}
