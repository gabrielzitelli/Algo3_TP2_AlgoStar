package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;
import edu.fiuba.algo3.modelo.Excepciones.ErrorElEdificioNoPuedeContratarUnidadades;
import edu.fiuba.algo3.modelo.Excepciones.ErrorElEdificioNoPuedeCrearUnidadades;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.UnidadesZerg.UnidadZerg;
import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;
import edu.fiuba.algo3.modelo.vida.Vida;

import java.util.ArrayList;

public abstract class Edificio {

    protected Vida vida;
    protected Coordenada coordenada;
    protected Recolectable estadoRecolectable;
    protected Cargable estadoCarga;
    protected EstadoMoho estadoMoho;
    protected int costoMineral;
    protected int costoGas;

    public void verificarConstruccion(Casilla unaCasilla){
        if (estadoRecolectable != null)
            unaCasilla.tieneEsteRecoletable(estadoRecolectable);
        if (estadoMoho != null)
            unaCasilla.tieneEsteMoho(estadoMoho);
        if (estadoCarga != null)
            unaCasilla.tieneEstaCarga(estadoCarga);

        coordenada = unaCasilla.obtenerCoordenada();
    }
    public ArrayList<Recurso> requisitosMateriales() {
        ArrayList<Recurso> requisitosMateriales = new ArrayList<>();
        requisitosMateriales.add(new Recurso(costoMineral));
        requisitosMateriales.add(new Recurso(costoGas));
        return requisitosMateriales;
    }

    public void aplicarAtaque(Ataque unAtaque) {
        try {
            this.vida.aplicarAtaque(unAtaque);
        }
        catch (Exception ErrorVidaLlegoACero){
            this.destruirEdificio();
        }
    }

    protected void destruirEdificio() {
        // Capaz estoy acoplando mucho edificio y mapa con esto
        Mapa elMapa = Mapa.obtener();
        // TODO solo criadero sabe sus coordenadas
        elMapa.destruirEdificio(coordenada);
    }

    public abstract void pasarTurno();
    public UnidadZerg crearUnidad(Fabrica unaFabrica) {
        throw new ErrorElEdificioNoPuedeCrearUnidadades();
    }
    public void contratarUnidad(UnidadZerg unidad) {
        throw new ErrorElEdificioNoPuedeContratarUnidadades();
    }
}
