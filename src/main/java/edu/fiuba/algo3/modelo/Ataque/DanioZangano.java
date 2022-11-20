package edu.fiuba.algo3.modelo.Ataque;

import edu.fiuba.algo3.modelo.Excepciones.ErrorZanganoNoPuedeAtacar;

public class DanioZangano implements DanioUnidad {

    @Override
    public Danio danioTerrestre() {
        throw new ErrorZanganoNoPuedeAtacar();
    }

    @Override
    public Danio danioAereo() {
        throw new ErrorZanganoNoPuedeAtacar();
    }
}
