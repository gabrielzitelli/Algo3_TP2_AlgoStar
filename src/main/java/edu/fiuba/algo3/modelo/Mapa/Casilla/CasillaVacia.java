package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Excepciones.*;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.UnidadZerg;
import edu.fiuba.algo3.modelo.Ataque.Ataque;

public class CasillaVacia extends Casilla{

    public CasillaVacia(Coordenada coordenada){
        estadoRecolectable = new NoRecolectable();
        estadoMoho = new SinMoho();
        estadoCarga = new SinCarga();
        this.coordenada = coordenada;
        this.unidad = null;
        this.superficie = new SuperficieTerrestre();
    }

    public CasillaVacia(Coordenada coordenada, Cargable estadoCarga, EstadoMoho estadoMoho, Recolectable estadoRecolectable, Superficie superficie) {
        this.estadoRecolectable = estadoRecolectable;
        this.estadoMoho = estadoMoho;
        this.estadoCarga = estadoCarga;
        this.coordenada = coordenada;
        this.superficie = superficie;
    }

    public Casilla construirEdificio(Edificio unEdificio){
        unEdificio.verificarConstruccion(this);
        CasillaOcupada casillaOcupada = new CasillaOcupada(coordenada, this.estadoCarga, this.estadoMoho, this.estadoRecolectable, this.superficie);
        casillaOcupada.establecerEdificio(unEdificio);
        return casillaOcupada;
    }

    public Coordenada obtenerCoordenada(){
        return this.coordenada;
    }

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

    public Edificio obtenerEdificio() {
        throw new ErrorNoExisteNingunEdificioEnEstaCasilla();
    }

    public Casilla colocarUnidad(Unidad unaUnidad){
        unaUnidad.verificarSuperficie(this.superficie);
        CasillaOcupada casillaOcupada = new CasillaOcupada(coordenada, this.estadoCarga, this.estadoMoho, this.estadoRecolectable, this.superficie);
        casillaOcupada.settearUnidad(unaUnidad);
        return casillaOcupada;
    }

    public void atacar(Casilla casillaAtacada){
        throw new ErrorUnaCasillaVaciaNoPuedeParticiparEnAtaque();
    }

    protected void recibirAtaque(Ataque unAtaque){
        throw new ErrorUnaCasillaVaciaNoPuedeParticiparEnAtaque();
    }

    public Casilla moverUnidadHacia(Casilla destino){
        throw new ErrorNoSePuedeMoverUnaUnidadQueNoExiste();
    }

    public Casilla quitarUnidad(){
        throw new ErrorNoSePuedeQuitarUnidadDeUnaCasillaVacia();
    }
}
