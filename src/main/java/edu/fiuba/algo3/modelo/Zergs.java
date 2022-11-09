package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero.*;

import java.util.LinkedList;

public class Zergs implements Imperio {

    private Recurso minerales;
    private Recurso gasVespeno;
    private LinkedList<Edificio> edificios;

    public Zergs(Recurso _minerales, Recurso _gasVespeno) {
        this.minerales = _minerales;
        this.gasVespeno = _gasVespeno;
        this.edificios = new LinkedList<>();
    }

    public Edificio construirCriadero(Zangano zangano) {
        minerales.consumir(50);

        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new SinRecurso());
        Edificio criadero = new Criadero(nodoCompatible, minerales, this);
        edificios.add(criadero);

        return criadero;
    }

    public Edificio construirExtractor(Zangano zangano) {
        minerales.consumir(100);

        NodoCompatible nodoCompatible = new NodoCompatible(new Moho(), new VolcanGasVespeno());
        Edificio extractor = new Extractor(nodoCompatible, gasVespeno);
        edificios.add(extractor);

        return extractor;
    }

    public Edificio construirReservaDeReproduccion(Zangano zangano) {
        minerales.consumir(150);

        NodoCompatible nodoCompatible = new NodoCompatible(new Moho(), new SinRecurso());
        Edificio reservaDeReproduccion = new ReservaDeReproduccion(nodoCompatible);
        edificios.add(reservaDeReproduccion);

        return reservaDeReproduccion;
    }

    public Edificio construirGuarida(Zangano zangano) {
        minerales.consumir(200);
        gasVespeno.consumir(100);

        NodoCompatible nodoCompatible = new NodoCompatible(new Moho(), new SinRecurso());
        Edificio guarida = new Guarida(nodoCompatible);
        edificios.add(guarida);

        return guarida;
    }

    public Edificio construirEspiral(Zangano zangano) {
        minerales.consumir(150);
        gasVespeno.consumir(100);

        NodoCompatible nodoCompatible = new NodoCompatible(new Moho(), new SinRecurso());
        Edificio espiral = new Espiral(nodoCompatible);
        edificios.add(espiral);

        return espiral;
    }

    public void terminarTurno(){
        // TODO
    }
}
