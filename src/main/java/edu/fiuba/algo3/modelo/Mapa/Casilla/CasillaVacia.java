package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoExisteNingunEdificioEnEstaCasilla;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeConstruirEdificioSobreOtroEdificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeDesconstruirUnEdificioNoCreado;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Unidad;
import edu.fiuba.algo3.modelo.UnidadesZerg.UnidadZerg;
import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;

public class CasillaVacia extends Casilla{

    public CasillaVacia(Coordenada coordenada){
        estadoRecolectable = new NoRecolectable();
        estadoMoho = new SinMoho();
        estadoCarga = new SinCarga();
        this.coordenada = coordenada;
        this.unidad = null;
    }

    public Casilla construirEdificio(Edificio unEdificio){
        unEdificio.verificarConstruccion(this);
        CasillaOcupada casillaOcupada = new CasillaOcupada(coordenada, this.estadoCarga, this.estadoMoho, this.estadoRecolectable);
        casillaOcupada.establecerEdificio(unEdificio);
        return casillaOcupada;
    }

    public CasillaVacia(Coordenada coordenada, Cargable estadoCarga, EstadoMoho estadoMoho, Recolectable estadoRecolectable){
        this.estadoRecolectable = estadoRecolectable;
        this.estadoMoho = estadoMoho;
        this.estadoCarga = estadoCarga;
        this.coordenada = coordenada;
    }

    public Coordenada obtenerCoordenada(){
        return this.coordenada;
    }

    @Override
    public void llenarDeMoho() {
        estadoMoho = new ConMoho();
    }

    public Casilla colocarUnidadZerg(UnidadZerg unaUnidadZerg){
        unaUnidadZerg.interaccionar(this);
        return new CasillaOcupada(coordenada);
    }
    
    public Casilla desconstruirEdificio(Coordenada coordenada){
        throw new ErrorNoSePuedeDesconstruirUnEdificioNoCreado();
    }

    @Override
    public Edificio obtenerEdificio() {
        throw new ErrorNoExisteNingunEdificioEnEstaCasilla();
    }

    public Casilla colocarUnidad(Unidad unaUnidad){
        CasillaOcupada casillaOcupada = new CasillaOcupada(coordenada, this.estadoCarga, this.estadoMoho, this.estadoRecolectable);
        casillaOcupada.settearUnidad(unaUnidad);
        return casillaOcupada;
    }

    public void atacar(Casilla casillaAtacada){
        throw new Error();
    }

    protected void recibirAtaque(Ataque unAtaque){
        throw new Error();
    }
}
