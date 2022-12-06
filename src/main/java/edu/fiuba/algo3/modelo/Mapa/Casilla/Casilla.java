package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Unidades.Ocupable;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.UnidadZerg;
import edu.fiuba.algo3.modelo.Ataque.Ataque;

public abstract class Casilla {

    protected Recolectable estadoRecolectable;
    protected Cargable estadoCarga;
    protected EstadoMoho estadoMoho;
    protected Revelable estadoRevelable;
    protected Superficie superficie;
    protected Ocupable ocupable;
    protected Coordenada coordenada;

    public abstract Casilla construirEdificio(Edificio unEdificio);
    public abstract void construirEdificioVerificacion(Edificio unEdificio);
    public abstract void llenarDeMoho();
    public abstract Casilla desconstruirEdificio(Coordenada coordenada);
    public abstract Ocupable obtenerOcupable();
    public abstract Edificio obtenerEdificio();
    public abstract Casilla colocarUnidad(Unidad unaUnidad);
    public abstract void atacar(Casilla casillaAtacada);
    public abstract void recibirAtaque(Ataque unAtaque);
    public abstract Casilla moverUnidadHacia(Casilla destino);
    public abstract Casilla quitarUnidad();
    public abstract boolean fuegoCompaniero(Unidad unidadZerg);

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

    public void tieneEstaSuperficie(Superficie superficieRequerida) {
        if (superficie.soyDiferenteA(superficieRequerida))
            throw new ErrorEdificioNoSePuedeConstruirEnEstaCasilla();
    }

    public boolean puedeMoverse(Superficie superficieAComparar) {
        return superficie.puedeMoverse(superficieAComparar);
    }

    public Coordenada obtenerCoordenada(){
        return this.coordenada;
    }

    public void cargarDeEnergia() {
        if(this.superficie.soyDiferenteA(new SuperficieAerea())){
            if (estadoCarga == null)
                estadoCarga = new ConCarga();

            estadoCarga = estadoCarga.cargar();
        }
    }

    public void descargarDeEnergia() {
        estadoCarga = estadoCarga.descargar();
    }

    public void revelar() {
        estadoRevelable = estadoRevelable.revelar();
    }

    public void desRevelar() {
        estadoRevelable = estadoRevelable.desRevelar();
    }

    public boolean tieneEsteRevelable(Revelable unRevelable) {
        return estadoRevelable.esIgualA(unRevelable);
    }

    public String toString() {
        String string = "";

        string += "superficie " + superficie.toString();
        string += " recurso " + estadoRecolectable.toString();
        string += " carga " + estadoCarga.toString();
        string += " moho " + estadoMoho.toString();
        string += " ocupable " + ocupable.toString();

        return string;
    }

}
