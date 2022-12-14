package edu.fiuba.algo3.modelo.Ataque;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoPuedeAtacarUnidadTerrestre;

public class DanioAereo implements TipoDanio {

    private final Danio danioAereo;

    public DanioAereo(int danioAereo) {
        this.danioAereo = new Danio(danioAereo);
    }

    @Override
    public Danio danioTerrestre() {
        throw new ErrorNoPuedeAtacarUnidadTerrestre();
    }

    @Override
    public Danio danioAereo() {
        return danioAereo;
    }
}
