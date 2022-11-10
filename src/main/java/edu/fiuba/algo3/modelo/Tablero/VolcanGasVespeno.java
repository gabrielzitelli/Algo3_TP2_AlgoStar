package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.excepciones.RecursosAgotados;

public class VolcanGasVespeno implements NodoRecurso {

    private final int cantidadGasDefault = 5000;
    private int cantidadDeGas;

    public VolcanGasVespeno() {
        cantidadDeGas = cantidadGasDefault;
    }

    public VolcanGasVespeno(int mineralInicial) {
        cantidadDeGas = mineralInicial;
    }

    @Override
    public boolean igualA(NodoRecurso otroRecurso) {
        return this.getClass().equals(otroRecurso.getClass());
    }

    @Override
    public int extraer(int cantidad) {
        if ((cantidadDeGas - cantidad) < 0) throw new RecursosAgotados();

        cantidadDeGas -= cantidad;
        return cantidad;
    }
}
