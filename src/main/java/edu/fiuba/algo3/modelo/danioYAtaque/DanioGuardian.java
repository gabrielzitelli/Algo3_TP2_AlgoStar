package edu.fiuba.algo3.modelo.danioYAtaque;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoPuedeAtacarUnidadVoladora;

public class DanioGuardian implements DanioUnidad {

    private Danio danioTerrestre;

    public DanioGuardian(int danioTerrestre){
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
