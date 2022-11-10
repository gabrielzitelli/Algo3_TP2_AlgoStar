package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero.*;

import java.util.LinkedList;

public class Protoss implements Imperio {

    private Tablero tablero;
    private Recurso minerales;
    private Recurso gasVespeno;
    private LinkedList<Edificio> edificios;

    public Protoss(Tablero _tablero,Recurso _minerales, Recurso _gasVespeno) {
        this.tablero = _tablero;
        this.minerales = _minerales;
        this.gasVespeno = _gasVespeno;
        this.edificios = new LinkedList<>();
    }

    public Edificio construirNexoMineral(Coordenadas ubicacion) {
        minerales.consumir(50);

        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new NodoMineral());
        Edificio nexoMineral = new NexoMineral(nodoCompatible, minerales);

        tablero.construir(nexoMineral, ubicacion);

        edificios.add(nexoMineral);

        return nexoMineral;
    }

    public Edificio construirPilon(Coordenadas ubicacion) {
        minerales.consumir(100);

        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new SinRecurso());
        Edificio pilon = new Pilon(tablero, nodoCompatible, ubicacion);
        tablero.construir(pilon, ubicacion);

        edificios.add(pilon);

        return pilon;
    }

    public Edificio construirAsimilador(Coordenadas ubicacion) {
        minerales.consumir(100);

        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new VolcanGasVespeno());
        Edificio asimilador = new Asimilador(nodoCompatible, gasVespeno);
        tablero.construir(asimilador, ubicacion);
        edificios.add(asimilador);

        return asimilador;
    }

    public Edificio construirAcceso(Coordenadas ubicacion) {
        minerales.consumir(150);

        NodoCompatible nodoCompatible = new NodoCompatible(new Energia(), new SinRecurso());
        Edificio acceso = new Acceso(nodoCompatible, ubicacion);
        tablero.construir(acceso, ubicacion);
        edificios.add(acceso);

        return acceso;
    }

    public Edificio construirPuertoEstelar(Coordenadas ubicacion) {
        minerales.consumir(150);
        gasVespeno.consumir(150);

        NodoCompatible nodoCompatible = new NodoCompatible(new Energia(), new SinRecurso());
        Edificio puertoEstelar = new PuertoEstelar(nodoCompatible, ubicacion);
        tablero.construir(puertoEstelar, ubicacion);
        edificios.add(puertoEstelar);

        return puertoEstelar;
    }

    @Override
    public void terminarTurno() {
        // TODO
    }
}
