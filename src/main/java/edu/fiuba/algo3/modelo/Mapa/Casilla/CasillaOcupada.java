package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Extractor;
import edu.fiuba.algo3.modelo.Unidades.Ocupable;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Excepciones.*;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.UnidadZerg;
import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;

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

    public void construirEdificioVerificacion(Edificio unEdificio){
        throw new ErrorNoSePuedeConstruirEdificioSobreOtroEdificio();
    }

    public Casilla construirEdificio(Edificio unEdificio) {
        throw new ErrorNoSePuedeConstruirEdificioSobreOtroEdificio();
    }

    public void llenarDeMoho() {
        if(ocupable.esDeEsteTipo(Edificio.class))
            estadoMoho = new SinMoho();
        else
            estadoMoho = new ConMoho();
    }

    public Casilla colocarUnidadZerg(UnidadZerg unaUnidadZerg) {
        throw new ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada();
    }

    public Casilla desconstruirEdificio(Coordenada coordenada) {
        return new CasillaVacia(coordenada, this.estadoCarga, this.estadoMoho, this.estadoRecolectable, this.superficie, this.estadoRevelable);
    }

    @Override
    public Ocupable obtenerOcupable() {
        return ocupable;
    }

    public Edificio obtenerEdificio() {
        return (Edificio) this.ocupable;
    }

    public void establecerEdificio(Edificio unEdificio) {
        this.ocupable = unEdificio;
    }

    public void settearUnidad(Unidad unaUnidad) {
        this.ocupable = unaUnidad;
    }

    public Casilla colocarUnidad(Unidad unaUnidad) {
        //Verificamos que se trate de un zangano y un extractor
        if (this.ocupable.getClass().equals(Extractor.class) &&
                unaUnidad.esIgualA(new Zangano())){
            ((Extractor) ocupable).contratarZangano(unaUnidad);
            return this;
        }

        throw new ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada();
    }

    public void atacar(Casilla casillaAtacada) {
        ((Unidad) ocupable).atacar(casillaAtacada);
    }

    public boolean esFuegoAliado(Imperio unImperio) {
        return ocupable.perteneceAImperio(unImperio);
    }

    public void recibirAtaque(Ataque unAtaque) {
        ocupable.recibirAtaque(unAtaque);
    }

    public Casilla moverUnidadHacia(Casilla destino) {
        return destino.colocarUnidad((Unidad) this.ocupable);
    }

    public Casilla quitarUnidad() {
        return new CasillaVacia(coordenada, this.estadoCarga, this.estadoMoho, this.estadoRecolectable, this.superficie, this.estadoRevelable);
    }

    @Override
    public void revelar() {
        super.revelar();
        ocupable.actualizarColocable(this);
    }

    @Override
    public void desRevelar() {
        super.desRevelar();
        ocupable.actualizarColocable(this);
    }
}
