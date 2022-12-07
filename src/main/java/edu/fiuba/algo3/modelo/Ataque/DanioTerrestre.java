package edu.fiuba.algo3.modelo.Ataque;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoPuedeAtacarUnidadVoladora;

public class DanioTerrestre implements TipoDanio {

    private Danio danioTerrestre;

    public DanioTerrestre(int danioTerrestre) {
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
