package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Unidades.Ocupable;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Excepciones.ErrorElEdificioNoPuedeContratarUnidadades;
import edu.fiuba.algo3.modelo.Excepciones.ErrorElEdificioNoPuedeCrearUnidadades;
import edu.fiuba.algo3.modelo.Imperio.*;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Vida.Vida;

import java.util.ArrayList;

public abstract class Edificio implements Ocupable {

    protected Vida vida;
    protected Coordenada coordenada;
    protected Recolectable estadoRecolectable;
    protected Cargable estadoCarga;
    protected EstadoMoho estadoMoho;
    protected Superficie superficieRequerida;
    protected int costoMineral;
    protected int costoGas;
    protected Mineral mineralDelImperio;
    protected Gas gasDelImperio;
    protected int suministroAportado;
    protected boolean estaDestruido = false;

    protected String identificador;

    public abstract void pasarTurno();

    public abstract boolean perteneceAImperio(Imperio imperio);

    public void verificarConstruccion(Casilla unaCasilla){
        if (estadoRecolectable != null)
            unaCasilla.tieneEsteRecoletable(estadoRecolectable);
        if (estadoMoho != null)
            unaCasilla.tieneEsteMoho(estadoMoho);
        if (estadoCarga != null)
            unaCasilla.tieneEstaCarga(estadoCarga);

        unaCasilla.tieneEstaSuperficie(superficieRequerida);

        coordenada = unaCasilla.obtenerCoordenada();
    }

    public void actualizarColocable(Casilla unaCasilla) {
        verificarConstruccion(unaCasilla);
    }

    public ArrayList<Recurso> requisitosMateriales() {
        ArrayList<Recurso> requisitosMateriales = new ArrayList<>();
        requisitosMateriales.add(new Mineral(costoMineral));
        requisitosMateriales.add(new Gas(costoGas));
        return requisitosMateriales;
    }

    public void recibirAtaque(Ataque unAtaque) {
        try {
            this.vida.aplicarAtaque(unAtaque);
        }
        catch (Exception ErrorVidaLlegoACero){
            this.destruirEdificio();
        }
    }
    public abstract boolean somosAliados(Unidad unaUnidad);

    protected void destruirEdificio() {
        Mapa elMapa = Mapa.obtener();
        elMapa.destruirEdificio(coordenada);
        this.estaDestruido = true;
    }

    public boolean esIgualA(Edificio edificio) {
        return this.getClass().equals(edificio.getClass());
    }

    public void crearUnidad(Fabrica unaFabrica) {
        throw new ErrorElEdificioNoPuedeCrearUnidadades();
    }

    public void contratarUnidad(Unidad unidad) {
        throw new ErrorElEdificioNoPuedeContratarUnidadades();
    }

    public void modificarPoblacion(Suministro poblacion){}

    public boolean estaDestruido(){
        return this.estaDestruido;
    }
    public void asignarRecursos(Mineral mineralesDelImperio, Gas gasDelImperio) {
        this.mineralDelImperio = mineralesDelImperio;
        this.gasDelImperio = gasDelImperio;
    }

    protected abstract String obtenerEstado();
    @Override
    public String toString() {
        return this.identificador + this.obtenerEstado() + vida.toString();
    }
    
    public boolean esDeEsteTipo(Class claseAAverificar){
        return Edificio.class.equals(claseAAverificar);
    }
}
