package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Extractor;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Unidades.Ocupable;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;

public class CasillaOcupada extends Casilla {

    public CasillaOcupada(Coordenada coordenada, Cargable estadoCarga, EstadoMoho estadoMoho, Recolectable estadoRecolectable, Superficie superficie, Revelable estadoRevelable, Ocupable unOcupable) {
        this.estadoRecolectable = estadoRecolectable;
        this.estadoMoho = estadoMoho;
        this.estadoCarga = estadoCarga;
        this.coordenada = coordenada;
        this.superficie = superficie;
        this.estadoRevelable = estadoRevelable;
        this.ocupable = unOcupable;
    }

    public void construirEdificioVerificacion(Edificio unEdificio){
        throw new ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada();
    }

    public Casilla colocarOcupable(Ocupable unOcupable) {
        //Verificamos que se trate de un zangano y un extractor
        if (this.ocupable.getClass().equals(Extractor.class) &&
                unOcupable.getClass().equals(Zangano.class)) {
            ((Extractor) ocupable).contratarZangano((Zangano) unOcupable);
            return this;
        }

        throw new ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada();
    }

    public void llenarDeMoho() {
        if(ocupable.esDeEsteTipo(Edificio.class))
            estadoMoho = new SinMoho();
        else
            estadoMoho = new ConMoho();
    }

    public Casilla quitarOcupable() {
        return new CasillaVacia(coordenada, this.estadoCarga, this.estadoMoho, this.estadoRecolectable, this.superficie, this.estadoRevelable);
    }

    @Override
    public Ocupable obtenerOcupable() {
        return ocupable;
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
        return destino.colocarOcupable(this.ocupable);
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
