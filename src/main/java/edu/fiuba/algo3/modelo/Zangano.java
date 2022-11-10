package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.excepciones.TerrenoNoCompatibleConEdificio;

public class Zangano implements Turno {

    private Tablero tablero;
    private Coordenadas posicion;
    private Recurso minerales;
    private NodoRecurso nodoMineral;
    private int unidadesPorTurno = 10;

    public Zangano(Tablero _tablero, Coordenadas _posicion, Recurso _minerales) {
        this.tablero = _tablero;
        this.posicion = _posicion;
        this.minerales = _minerales;
    }

    public Coordenadas getPosicion() {
        return posicion;
    }

    public void extraerMineral() {
        tablero.asignarUnidad(this, posicion);
    }

    public void accionDeTurno(){
        minerales.depositar(nodoMineral.extraer(unidadesPorTurno));
    }

    public void esCompatible(NodoRecurso nodoRecurso) {
        NodoMineral requisito = new NodoMineral();
        if(!nodoRecurso.igualA(requisito)) {
            throw new TerrenoNoCompatibleConEdificio();
        }
        nodoMineral = nodoRecurso;
    }
}
