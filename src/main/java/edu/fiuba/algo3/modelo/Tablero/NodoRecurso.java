package edu.fiuba.algo3.modelo.Tablero;

public interface NodoRecurso {
    boolean igualA(NodoRecurso nodoRecurso);

    int extraer(int cantidad);
}
