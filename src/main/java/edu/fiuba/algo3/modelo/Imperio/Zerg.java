package edu.fiuba.algo3.modelo.Imperio;

import edu.fiuba.algo3.modelo.EdificioZerg.*;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;

import java.util.LinkedList;


public class Zerg extends Imperio{

    public Zerg(){
        mineralesDelImperio = new Recurso(0);
        gasDelImperio = new Recurso(0);
        edificios = new LinkedList<>();
    }
    public void construirCriadero(Coordenada coordenada){
        Criadero criadero = new Criadero();
        this.construirEdificio(criadero, coordenada);
    }
    public void construirReservaDeReproduccion(Coordenada coordenada){
        ReservaDeReproduccion reserva = new ReservaDeReproduccion();
        this.construirEdificio(reserva, coordenada);
    }
    public void construirExtractor(Coordenada coordenada){
        Extractor extractor = new Extractor(gasDelImperio);
        this.construirEdificio(extractor, coordenada);
    }
    public void construirGuarida(Coordenada coordenada){
        this.comprobarRequisitos(Guarida.requisitos());
        Guarida guarida = new Guarida();
        this.construirEdificio(guarida, coordenada);
    }
    public void construirEspiral(Coordenada coordenada){
        this.comprobarRequisitos(Espiral.requisitos());
        Espiral espiral = new Espiral();
        this.construirEdificio(espiral, coordenada);
    }
}
