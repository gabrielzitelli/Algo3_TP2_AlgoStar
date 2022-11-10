package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero.NodoRecurso;

public class Zangano implements Turno {
    private Coordenadas posicion;
    private Recurso minerales;

    public Zangano(Coordenadas _posicion, Recurso _minerales) {
        this.posicion = _posicion;
        this.minerales = _minerales;
    }


    public void extraerMineral(){
        //recursoQueExtrae.modificarRecurso(minerales , 10);
        // TODO
    }

    public void accionDeTurno(){
        this.extraerMineral();
        // TODO
    }


}
