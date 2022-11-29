package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Ataque.Ocupable;
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
        this.estadoRevelable = new SinRevelar();
        this.coordenada = coordenada;
        this.ocupable = null;
        this.superficie = new SuperficieTerrestre();
    }

    public CasillaVacia(Coordenada coordenada, Cargable estadoCarga, EstadoMoho estadoMoho, Recolectable estadoRecolectable, Superficie superficie, Revelable estadoRevelable) {
        this.estadoRecolectable = estadoRecolectable;
        this.estadoMoho = estadoMoho;
        this.estadoCarga = estadoCarga;
        this.coordenada = coordenada;
        this.superficie = superficie;
        this.estadoRevelable = estadoRevelable;
    }

    public Casilla construirEdificio(Edificio unEdificio){
        unEdificio.verificarConstruccion(this);
        CasillaOcupada casillaOcupada = new CasillaOcupada(coordenada, this.estadoCarga, this.estadoMoho, this.estadoRecolectable, this.superficie, this.estadoRevelable);
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

    @Override
    public Ocupable obtenerOcupable() {
        throw new ErrorCasillaVacia();
    }

    public Edificio obtenerEdificio() {
        throw new ErrorNoExisteNingunEdificioEnEstaCasilla();
    }

    public Casilla colocarUnidad(Unidad unaUnidad) {
        unaUnidad.verificarColocable(this);
        CasillaOcupada casillaOcupada = new CasillaOcupada(coordenada, this.estadoCarga, this.estadoMoho, this.estadoRecolectable, this.superficie, this.estadoRevelable);
        casillaOcupada.settearUnidad(unaUnidad);
        return casillaOcupada;
    }

    public void atacar(Casilla casillaAtacada){
        throw new ErrorUnaCasillaVaciaNoPuedeParticiparEnAtaque();
    }

    public void recibirAtaque(Ataque unAtaque){
        throw new ErrorUnaCasillaVaciaNoPuedeParticiparEnAtaque();
    }

    public Casilla moverUnidadHacia(Casilla destino){
        throw new ErrorNoSePuedeMoverUnaUnidadQueNoExiste();
    }

    public Casilla quitarUnidad(){
        return new CasillaVacia(coordenada, this.estadoCarga, this.estadoMoho, this.estadoRecolectable, this.superficie, this.estadoRevelable);
    }
}
