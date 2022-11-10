package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Recurso;

public class NodoMineral implements NodoRecurso {

    public int cantidadMinerales;

    public NodoMineral (){
        this.cantidadMinerales = 2000;
    }

    @Override
    public boolean igualA(NodoRecurso nodoRecurso) {
        NodoMineral nodoMineral = new NodoMineral();
        return nodoMineral.getClass().equals(nodoRecurso.getClass());

    }

    public void modificarRecurso(Recurso _minerales , int cantidadExtraccion){
        cantidadMinerales = (cantidadMinerales - cantidadExtraccion);
        _minerales.depositar(cantidadExtraccion);
    }
}
