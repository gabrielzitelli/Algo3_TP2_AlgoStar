package edu.fiuba.algo3.modelo.Ataque;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoPuedeAtacarUnidadTerrestre;

public class DanioDevorador implements DanioUnidad {
    private Danio danioAereo;

    public DanioDevorador(int danioAereo){
        this.danioAereo = new Danio(danioAereo);
    }

    public Danio danioTerrestre() {
        throw new ErrorNoPuedeAtacarUnidadTerrestre();
    }

    public Danio danioAereo() { return danioAereo; }
}
