package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Recurso;

public interface NodoRecurso {
    boolean igualA(NodoRecurso nodoRecurso);

    void modificarRecurso(Recurso recurso , int cantidadExtraccion);

}
