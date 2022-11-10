package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Recurso;

public class SinRecurso implements NodoRecurso{
    @Override
    public boolean igualA(NodoRecurso nodoRecurso) {
        SinRecurso sinRecurso = new SinRecurso();
        return sinRecurso.getClass().equals(nodoRecurso.getClass());
    }
    public void modificarRecurso(Recurso _minerales , int cantidadExtraccion){
    }
}
