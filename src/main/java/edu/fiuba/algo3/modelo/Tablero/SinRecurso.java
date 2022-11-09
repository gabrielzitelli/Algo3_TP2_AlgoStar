package edu.fiuba.algo3.modelo.Tablero;

public class SinRecurso implements NodoRecurso{
    @Override
    public boolean igualA(NodoRecurso nodoRecurso) {
        SinRecurso sinRecurso = new SinRecurso();
        return sinRecurso.getClass().equals(nodoRecurso.getClass());
    }
}
