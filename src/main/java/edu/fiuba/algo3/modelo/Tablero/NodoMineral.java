package edu.fiuba.algo3.modelo.Tablero;

public class NodoMineral implements NodoRecurso {
    @Override
    public boolean igualA(NodoRecurso nodoRecurso) {
        NodoMineral nodoMineral = new NodoMineral();
        return nodoMineral.getClass().equals(nodoRecurso.getClass());

    }
}
