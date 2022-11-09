package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero.NodoRecurso;
import edu.fiuba.algo3.modelo.Tablero.Terreno;

public class NodoCompatible {

    private Terreno terreno;
    private NodoRecurso nodoRecurso;
    public NodoCompatible(Terreno terreno, NodoRecurso recurso) {
        this.terreno = terreno;
        this.nodoRecurso = recurso;
    }

    public boolean esCompatible(Terreno terreno, NodoRecurso nodoRecurso) {
        return this.terreno.getClass().equals(terreno.getClass());
    }
}
