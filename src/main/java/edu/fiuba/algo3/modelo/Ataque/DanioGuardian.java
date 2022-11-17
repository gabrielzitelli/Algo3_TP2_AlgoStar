package edu.fiuba.algo3.modelo.Ataque;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoPuedeAtacarUnidadVoladora;

public class DanioGuardian implements DanioUnidad {

    private Danio danioTerrestre;

    public DanioGuardian(int danioTerrestre){
        this.danioTerrestre = new Danio(danioTerrestre);
    }

    public Danio danioTerrestre() {
        return danioTerrestre;
    }

    public Danio danioAereo() {
        throw new ErrorNoPuedeAtacarUnidadVoladora();
    }
}
