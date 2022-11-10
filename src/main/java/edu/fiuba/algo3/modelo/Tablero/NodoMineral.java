package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.excepciones.RecursosAgotados;

public class NodoMineral implements NodoRecurso {

    private final int cantidadMineralDefault = 2000;
    private int cantidadDeMineral;

    public NodoMineral() {
        cantidadDeMineral = cantidadMineralDefault;
    }

    public NodoMineral(int mineralInicial) {
        cantidadDeMineral = mineralInicial;
    }

    @Override
    public boolean igualA(NodoRecurso otroRecurso) {
        return this.getClass().equals(otroRecurso.getClass());
    }

    @Override
    public int extraer(int cantidad) {
        if ((cantidadDeMineral - cantidad) < 0) throw new RecursosAgotados();

        cantidadDeMineral -= cantidad;
        return cantidad;
    }
}
