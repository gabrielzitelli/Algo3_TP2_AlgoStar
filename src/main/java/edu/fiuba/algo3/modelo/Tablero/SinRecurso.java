package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.excepciones.RecursosAgotados;

public class SinRecurso implements NodoRecurso{

    @Override
    public boolean igualA(NodoRecurso otroRecurso) {
        return this.getClass().equals(otroRecurso.getClass());
    }

    @Override
    public int extraer(int cantidad) {
        throw new RecursosAgotados();
    }
}
