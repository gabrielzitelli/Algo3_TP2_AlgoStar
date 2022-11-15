package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.Unidad;
import edu.fiuba.algo3.modelo.UnidadesZerg.UnidadZerg;
import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;
import edu.fiuba.algo3.modelo.danioYAtaque.Danio;

public abstract class Casilla {

    protected Recolectable estadoRecolectable;
    protected Cargable estadoCarga;
    protected EstadoMoho estadoMoho;

    protected Superficie superficie;

    protected Unidad unidad;

    protected Coordenada coordenada;

    public abstract Casilla construirEdificio(Edificio unEdificio);

    public void colocarMaterial(SiRecolectable materialAColocar){
        estadoRecolectable = materialAColocar;
    }
    public void colocarSuperficie(Superficie superficieAColocar) {
        superficie = superficieAColocar;
    }

    public MaterialBruto obtenerMaterial(){
        return estadoRecolectable.obtenerMaterial();
    }

    public void tieneEsteRecoletable(Recolectable recolectableRequerido){
        if(estadoRecolectable.soyDiferenteA(recolectableRequerido))
            throw new ErrorEdificioNoSePuedeConstruirEnEstaCasilla();
    }

    public void tieneEstaCarga(Cargable cargaRequerido){
        if(estadoCarga.soyDiferenteA(cargaRequerido))
            throw new ErrorEdificioNoSePuedeConstruirEnEstaCasilla();
    }

    public void tieneEsteMoho(EstadoMoho mohoRequerido){
        if(estadoMoho.soyDiferenteA(mohoRequerido))
            throw new ErrorEdificioNoSePuedeConstruirEnEstaCasilla();
    }

    public Coordenada obtenerCoordenada(){
        return this.coordenada;
    }

    public abstract void llenarDeMoho();

    public void cargarDeEnergia() {
        if (estadoCarga == null){
            estadoCarga = new ConCarga();
        }
        estadoCarga = estadoCarga.cargar();
    }

    public abstract Casilla colocarUnidadZerg(UnidadZerg unaUnidadZerg);
    public abstract Casilla desconstruirEdificio(Coordenada coordenada);
    public abstract Edificio obtenerEdificio();

    public abstract Casilla colocarUnidad(Unidad unaUnidad);

    public abstract void atacar(Casilla casillaAtacada);

    protected abstract void recibirAtaque(Ataque unAtaque);
}
