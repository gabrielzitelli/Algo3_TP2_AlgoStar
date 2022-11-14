package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.EdificioZerg.Criadero;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeConstruirEdificioSobreOtroEdificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorPosicionOcupada;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.UnidadesZerg.UnidadZerg;

public class CasillaOcupada extends Casilla{
    Edificio edificioQueOcupa = null;
    public CasillaOcupada(Coordenada coordenada){
        this.coordenada = coordenada;
    }

    public CasillaOcupada(Coordenada coordenada, Cargable estadoCarga, EstadoMoho estadoMoho, Recolectable estadoRecolectable){
        this.estadoRecolectable = estadoRecolectable;
        this.estadoMoho = estadoMoho;
        this.estadoCarga = estadoCarga;
        this.coordenada = coordenada;
    }

    public Casilla construirEdificio(Edificio unEdificio){
        throw new ErrorNoSePuedeConstruirEdificioSobreOtroEdificio();
    }

    public Casilla colocarUnidadZerg(UnidadZerg unaUnidadZerg){
        throw new ErrorPosicionOcupada();
    }
     
    public Casilla desconstruirEdificio(Coordenada coordenada){
        Casilla nuevaCasillaSinEdificio = new CasillaVacia(coordenada, this.estadoCarga, this.estadoMoho, this.estadoRecolectable);
        return  nuevaCasillaSinEdificio;
    }

    @Override
    public Edificio obtenerEdificio() {
        return edificioQueOcupa;
    }
    public void establecerEdificio(Edificio unEdificio) {
        this.edificioQueOcupa = unEdificio;
    }
}
