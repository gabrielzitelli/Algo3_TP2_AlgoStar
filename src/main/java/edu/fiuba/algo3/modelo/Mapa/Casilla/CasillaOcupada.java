package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeConstruirEdificioSobreOtroEdificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeMoverUnaUnidadQueNoExiste;
import edu.fiuba.algo3.modelo.Excepciones.ErrorPosicionOcupada;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Unidad;
import edu.fiuba.algo3.modelo.UnidadesZerg.UnidadZerg;
import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;

public class CasillaOcupada extends Casilla {
    Edificio edificioQueOcupa = null;

    public CasillaOcupada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public CasillaOcupada(Coordenada coordenada, Cargable estadoCarga, EstadoMoho estadoMoho, Recolectable estadoRecolectable, Superficie superficie) {
        this.estadoRecolectable = estadoRecolectable;
        this.estadoMoho = estadoMoho;
        this.estadoCarga = estadoCarga;
        this.coordenada = coordenada;
        this.superficie = superficie;
    }

    public Casilla construirEdificio(Edificio unEdificio) {
        throw new ErrorNoSePuedeConstruirEdificioSobreOtroEdificio();
    }

    @Override
    public void llenarDeMoho() {
        estadoMoho = new SinMoho();
    }

    public Casilla colocarUnidadZerg(UnidadZerg unaUnidadZerg) {
        throw new ErrorPosicionOcupada();
    }

    public Casilla desconstruirEdificio(Coordenada coordenada) {
        Casilla nuevaCasillaSinEdificio = new CasillaVacia(coordenada, this.estadoCarga, this.estadoMoho, this.estadoRecolectable, this.superficie);
        return nuevaCasillaSinEdificio;
    }

    @Override
    public Edificio obtenerEdificio() {
        return edificioQueOcupa;
    }

    public void establecerEdificio(Edificio unEdificio) {
        this.edificioQueOcupa = unEdificio;
    }

    public void settearUnidad(Unidad unaUnidad) {
        this.unidad = unaUnidad;
    }

    public Casilla colocarUnidad(Unidad unaUnidad) {
        throw new ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada();
    }

    public void atacar(Casilla casillaAtacada) {
        casillaAtacada.recibirAtaque(unidad.atacar());
    }

    protected void recibirAtaque(Ataque unAtaque) {
        unidad.recibirAtaque(superficie.conseguirTipoDeAtaque(unAtaque));
    }

    public Casilla moverUnidadHacia(Casilla destino) {
        if (this.unidad == null)
            throw new ErrorNoSePuedeMoverUnaUnidadQueNoExiste();

        return destino.colocarUnidad(this.unidad);
    }

    public Casilla quitarUnidad(){
        return new CasillaVacia(coordenada, this.estadoCarga, this.estadoMoho, this.estadoRecolectable, this.superficie);
    }
}
