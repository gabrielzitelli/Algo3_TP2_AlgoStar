package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Ataque.Ocupable;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Excepciones.*;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.UnidadZerg;
import edu.fiuba.algo3.modelo.Ataque.Ataque;

public class CasillaOcupada extends Casilla {

    public CasillaOcupada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public CasillaOcupada(Coordenada coordenada, Cargable estadoCarga, EstadoMoho estadoMoho, Recolectable estadoRecolectable, Superficie superficie, Revelable estadoRevelable) {
        this.estadoRecolectable = estadoRecolectable;
        this.estadoMoho = estadoMoho;
        this.estadoCarga = estadoCarga;
        this.coordenada = coordenada;
        this.superficie = superficie;
        this.estadoRevelable = estadoRevelable;
    }

    public Casilla construirEdificio(Edificio unEdificio) {
        throw new ErrorNoSePuedeConstruirEdificioSobreOtroEdificio();
    }

    public void llenarDeMoho() {
        estadoMoho = new SinMoho();
    }

    public Casilla colocarUnidadZerg(UnidadZerg unaUnidadZerg) {
        throw new ErrorPosicionOcupada();
    }

    public Casilla desconstruirEdificio(Coordenada coordenada) {
        Casilla nuevaCasillaSinEdificio = new CasillaVacia(coordenada, this.estadoCarga, this.estadoMoho, this.estadoRecolectable, this.superficie, this.estadoRevelable);
        return nuevaCasillaSinEdificio;
    }

    @Override
    public Ocupable obtenerOcupable() {
        return ocupable;
    }

    public Edificio obtenerEdificio() {
        Edificio edificio = (Edificio) this.ocupable;
        return edificio;
    }

    public void establecerEdificio(Edificio unEdificio) {
        this.ocupable = unEdificio;
    }

    public void settearUnidad(Unidad unaUnidad) {
        this.ocupable = unaUnidad;
    }

    public Casilla colocarUnidad(Unidad unaUnidad) {
        throw new ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada();
    }

    public void atacar(Casilla casillaAtacada) {
        Unidad unidad = (Unidad) ocupable;

        if (Mapa.obtener().estaDentroDeRango(coordenada,casillaAtacada, unidad.rangoDeAtaque()))
            unidad.atacar(casillaAtacada);
        else
            throw new ErrorLaUnidadNoPuedeAtacarFueraDeSuRango();
    }

    public void recibirAtaque(Ataque unAtaque) {
        ocupable.recibirAtaque(unAtaque);
    }

    public Casilla moverUnidadHacia(Casilla destino) {
        if (this.ocupable == null)
            throw new ErrorNoSePuedeMoverUnaUnidadQueNoExiste();

        return destino.colocarUnidad((Unidad) this.ocupable);
    }

    public Casilla quitarUnidad() {
        return new CasillaVacia(coordenada, this.estadoCarga, this.estadoMoho, this.estadoRecolectable, this.superficie, this.estadoRevelable);
    }
}
