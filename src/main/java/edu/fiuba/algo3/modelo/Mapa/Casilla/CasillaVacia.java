package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorUnaCasillaVaciaNoPuedeParticiparEnAtaque;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Unidades.Ocupable;
import edu.fiuba.algo3.modelo.Unidades.SinOcupar;

public class CasillaVacia extends Casilla{

    public CasillaVacia(Coordenada coordenada){
        ocupable = new SinOcupar();
        estadoRecolectable = new NoRecolectable();
        estadoMoho = new SinMoho();
        estadoCarga = new SinCarga();
        this.estadoRevelable = new SinRevelar();
        this.coordenada = coordenada;
        this.ocupable = new SinOcupar();
        this.superficie = new SuperficieTerrestre();
    }

    public CasillaVacia(Coordenada coordenada, Cargable estadoCarga, EstadoMoho estadoMoho, Recolectable estadoRecolectable, Superficie superficie, Revelable estadoRevelable) {
        this.estadoRecolectable = estadoRecolectable;
        this.estadoMoho = estadoMoho;
        this.estadoCarga = estadoCarga;
        this.coordenada = coordenada;
        this.superficie = superficie;
        this.ocupable = new SinOcupar();
        this.estadoRevelable = estadoRevelable;
    }

    public void construirEdificioVerificacion(Edificio unEdificio){
        unEdificio.verificarColocable(this);
    }

    public Casilla colocarOcupable(Ocupable unOcupable) {
        unOcupable.verificarColocable(this);
        return new CasillaOcupada(coordenada, this.estadoCarga, this.estadoMoho, this.estadoRecolectable,
                this.superficie, this.estadoRevelable, unOcupable);
    }

    public Coordenada obtenerCoordenada(){
        return this.coordenada;
    }

    public void llenarDeMoho() {
        if(this.superficie.soyDiferenteA(new SuperficieAerea()))
            estadoMoho = new ConMoho();
    }

    @Override
    public Ocupable obtenerOcupable() {
        return ocupable;
    }

    public Casilla quitarOcupable() {
        return this;
    }

    public void atacar(Casilla casillaAtacada){
        throw new ErrorUnaCasillaVaciaNoPuedeParticiparEnAtaque();
    }

    public boolean esFuegoAliado(Imperio unImperio) {
        return false;
    }

    public boolean tieneEsteOcupable(Ocupable ocupable) {
        return false;
    }

    public void recibirAtaque(Ataque unAtaque){
        ocupable.recibirAtaque(unAtaque);
    }

    public Casilla moverUnidadHacia(Casilla destino){
        return destino;
    }
}
