package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero.*;
import java.util.LinkedList;

public class Zergs extends Imperio {

    private LinkedList<Zangano> zanganos;

    public Zergs(Tablero _tablero, Recurso _minerales, Recurso _gasVespeno) {
        this.minerales = _minerales;
        this.gasVespeno = _gasVespeno;
        this.edificios = new LinkedList<>();
        this.tablero = _tablero;
        this.zanganos = new LinkedList<>();
    }

    public Edificio construirCriadero(Zangano zangano, Coordenadas posicion) {
        minerales.consumir(50);

        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new SinRecurso());
        Edificio criadero = new Criadero(tablero, nodoCompatible, posicion, this);
        tablero.construir(criadero, zangano.getPosicion());
        edificios.add(criadero);

        return criadero;
    }

    public Edificio construirExtractor(Zangano zangano) {
        minerales.consumir(100);

        NodoCompatible nodoCompatible = new NodoCompatible(new Moho(), new VolcanGasVespeno());
        Edificio extractor = new Extractor(nodoCompatible, gasVespeno);
        tablero.construir(extractor,zangano.getPosicion());
        edificios.add(extractor);

        return extractor;
    }

    public Edificio construirReservaDeReproduccion(Zangano zangano) {
        minerales.consumir(150);

        NodoCompatible nodoCompatible = new NodoCompatible(new Moho(), new SinRecurso());
        Edificio reservaDeReproduccion = new ReservaDeReproduccion(nodoCompatible);
        tablero.construir(reservaDeReproduccion,zangano.getPosicion());
        edificios.add(reservaDeReproduccion);

        return reservaDeReproduccion;
    }

    public Edificio construirGuarida(Zangano zangano) {
        minerales.consumir(200);
        gasVespeno.consumir(100);
        coomprobarRequisitos(Guarida.requisitos());

        NodoCompatible nodoCompatible = new NodoCompatible(new Moho(), new SinRecurso());
        Edificio guarida = new Guarida(nodoCompatible);
        tablero.construir(guarida, zangano.getPosicion());
        edificios.add(guarida);

        return guarida;
    }

    public Edificio construirEspiral(Zangano zangano) {
        minerales.consumir(150);
        gasVespeno.consumir(100);
        coomprobarRequisitos(Espiral.requisitos());

        NodoCompatible nodoCompatible = new NodoCompatible(new Moho(), new SinRecurso());
        Edificio espiral = new Espiral(nodoCompatible);
        tablero.construir(espiral,zangano.getPosicion());
        edificios.add(espiral);

        return espiral;
    }

    public Zangano reclutarZangano(Coordenadas ubicacion) {
        Zangano zangano = new Zangano(tablero, ubicacion, minerales);
        zanganos.add(zangano);
        return zangano;
    }

    public void terminarTurno(){
        // TODO
    }
}
