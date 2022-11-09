package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero.NodoRecurso;
import edu.fiuba.algo3.modelo.Tablero.Terreno;

public abstract class Edificio implements Turno, Daniable {

    NodoCompatible nodoCompatible;
    public void recibirDanio(int danio){
    }

    public boolean esCompatible(Terreno terreno, NodoRecurso nodoRecurso) {
        return nodoCompatible.esCompatible(terreno, nodoRecurso);
    }
}
