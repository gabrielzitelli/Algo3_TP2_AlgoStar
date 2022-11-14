package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;

public abstract class Casilla {

    protected Recolectable estadoRecolectable;
    protected Cargable estadoCarga;
    protected EstadoMoho estadoMoho;

    protected Coordenada coordenada;

    public abstract Casilla construirEdificio(Edificio unEdificio);

    public void colocarMaterial(SiRecolectable materialAColocar){
        estadoRecolectable = materialAColocar;
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

    public void llenarDeMoho(){
        estadoMoho = new ConMoho();
    }

    public void cargarDeEnergia() {
        if (estadoCarga == null){
            estadoCarga = new ConCarga();
        }
        estadoCarga = estadoCarga.cargar();
    }

    public abstract Casilla desonstruirEdificio(Coordenada coordenada);
}
