package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.Fabrica;
import edu.fiuba.algo3.modelo.Excepciones.ErrorElEdificioNoPuedeContratarUnidades;
import edu.fiuba.algo3.modelo.Excepciones.ErrorElEdificioNoPuedeCrearUnidades;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada;
import edu.fiuba.algo3.modelo.Imperio.*;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Ocupable;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Vida.Vida;

import java.util.ArrayList;

public abstract class Edificio implements Ocupable {

    protected Vida vida;
    protected Coordenada coordenada;
    protected Recolectable estadoRecolectable;
    protected Cargable estadoCargaRequerido;
    protected EstadoMoho estadoMohoRequerido;
    protected Superficie superficieRequerida;
    protected int costoMineral = 0;
    protected int costoGas = 0;
    protected Mineral mineralDelImperio;
    protected Gas gasDelImperio;
    protected int suministroAportado;
    protected boolean estaDestruido = false;

    protected String identificador;

    public abstract void pasarTurno();

    public abstract boolean perteneceAImperio(Imperio imperio);
    public abstract ArrayList<Edificio> obtenerRequisitosEdilicios();

    public void verificarColocable(Casilla unaCasilla){
        if (estadoRecolectable != null)
            unaCasilla.tieneEsteRecoletable(estadoRecolectable);
        if (estadoMohoRequerido != null)
            unaCasilla.tieneEsteMoho(estadoMohoRequerido);
        if (estadoCargaRequerido != null)
            unaCasilla.tieneEstaCarga(estadoCargaRequerido);

        unaCasilla.tieneEstaSuperficie(superficieRequerida);

        coordenada = unaCasilla.obtenerCoordenada();
    }

    public void actualizarColocable(Casilla unaCasilla) {
        verificarColocable(unaCasilla);
    }

    public ArrayList<Recurso> requisitosMateriales() {
        ArrayList<Recurso> requisitosMateriales = new ArrayList<>();
        requisitosMateriales.add(new Mineral(costoMineral));
        requisitosMateriales.add(new Gas(costoGas));
        return requisitosMateriales;
    }

    public void recibirAtaque(Ataque unAtaque) {
        try {
            this.vida.aplicarAtaque(unAtaque.ataqueTerrestre());
        }
        catch (Exception ErrorVidaLlegoACero){
            this.destruirEdificio();
        }
    }

    protected void destruirEdificio() {
        Mapa elMapa = Mapa.obtener();
        elMapa.quitarOcupable(coordenada);
        this.estaDestruido = true;
    }

    public boolean esIgualA(Edificio edificio) {
        return this.getClass().equals(edificio.getClass());
    }

    public void crearUnidad(Fabrica unaFabrica) {
        throw new ErrorElEdificioNoPuedeCrearUnidades();
    }

    public void contratarUnidad(Unidad unidad) {
        throw new ErrorElEdificioNoPuedeContratarUnidades();
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
        return "ocupable " + this.identificador + " costoMineral " + this.costoMineral + " costoGas " + this.costoGas + " estado " + this.obtenerEstado() + vida.toString();
    }
    
    public boolean esDeEsteTipo(Class claseAAverificar){
        return Edificio.class.equals(claseAAverificar);
    }

    public void construirSobreCasillaOcupadaVerificacion(Ocupable ocupable, CasillaVacia copiaVaciaDeUnaCasilla){
        throw new ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada();
    }
}
