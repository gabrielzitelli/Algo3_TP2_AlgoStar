package edu.fiuba.algo3.modelo.Ataque;

import edu.fiuba.algo3.modelo.Excepciones.ErrorUnidadNoPuedeAtacar;

public class DanioZangano implements DanioUnidad {

    @Override
    public Danio danioTerrestre() {
        throw new ErrorUnidadNoPuedeAtacar();
    }

    @Override
    public Danio danioAereo() {
        throw new ErrorUnidadNoPuedeAtacar();
    }
}
