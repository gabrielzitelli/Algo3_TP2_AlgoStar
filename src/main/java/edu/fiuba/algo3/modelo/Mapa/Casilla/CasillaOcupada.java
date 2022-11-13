package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeConstruirEdificioSobreOtroEdificio;

public class CasillaOcupada extends Casilla{

    public Casilla construirEdificio(Edificio unEdificio){
        throw new ErrorNoSePuedeConstruirEdificioSobreOtroEdificio();
    }
}
