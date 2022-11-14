package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeConstruirEdificioSobreOtroEdificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorPosicionOcupada;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.UnidadesZerg.UnidadZerg;

public class CasillaOcupada extends Casilla{

    public CasillaOcupada(Coordenada coordenada){
        this.coordenada = coordenada;
    }

    public Casilla construirEdificio(Edificio unEdificio){
        throw new ErrorNoSePuedeConstruirEdificioSobreOtroEdificio();
    }

    public Casilla colocarUnidadZerg(UnidadZerg unaUnidadZerg){
        throw new ErrorPosicionOcupada();
    }
}
