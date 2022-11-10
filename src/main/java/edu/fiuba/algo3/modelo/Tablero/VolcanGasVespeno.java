package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Recurso;

public class VolcanGasVespeno implements NodoRecurso {

    private int cantidadGas ;

    public VolcanGasVespeno(){
        this.cantidadGas = 5000;
    }
    @Override
    public boolean igualA(NodoRecurso otroRecurso) {
        VolcanGasVespeno volcan = new VolcanGasVespeno();
        return volcan.getClass().equals(otroRecurso.getClass());
    }

    public void modificarRecurso(Recurso _gasVespeno , int cantidadExtraccion){
        cantidadGas = (cantidadGas - cantidadExtraccion);
        _gasVespeno.depositar(cantidadExtraccion);
    }
}
