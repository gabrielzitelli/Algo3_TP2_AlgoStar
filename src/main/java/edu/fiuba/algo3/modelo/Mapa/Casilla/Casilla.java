package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.Unidades.Ocupable;

public abstract class Casilla {

    protected Recolectable estadoRecolectable;
    protected Cargable estadoCarga;
    protected EstadoMoho estadoMoho;
    protected Revelable estadoRevelable;
    protected Superficie superficie;
    protected Ocupable ocupable;
    protected Coordenada coordenada;
    public abstract boolean tieneEsteOcupable(Ocupable ocupable);
    public abstract Casilla colocarOcupable(Ocupable unOcupable);
    public abstract void construirEdificioVerificacion(Edificio unEdificio);
    public abstract void llenarDeMoho();
    public abstract Ocupable obtenerOcupable();
    public abstract Casilla quitarOcupable();
    public abstract void atacar(Casilla casillaAtacada);
    public abstract boolean esFuegoAliado(Imperio unImperio);
    public abstract void recibirAtaque(Ataque unAtaque);
    public abstract Casilla moverUnidadHacia(Casilla destino);

    public void colocarMaterial(Recolectable materialAColocar){
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
        return !superficie.puedeMoverse(superficieAComparar);
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
        string += " " + ocupable.toString();

        return string;
    }

}
