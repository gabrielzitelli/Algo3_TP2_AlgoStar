package edu.fiuba.algo3.modelo.Tablero;

public class VolcanGasVespeno implements NodoRecurso {
    @Override
    public boolean igualA(NodoRecurso otroRecurso) {
        VolcanGasVespeno volcan = new VolcanGasVespeno();
        return volcan.getClass().equals(otroRecurso.getClass());
    }
}
