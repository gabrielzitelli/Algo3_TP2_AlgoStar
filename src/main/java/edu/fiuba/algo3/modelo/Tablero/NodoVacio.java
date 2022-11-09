package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Edificio;

public class NodoVacio implements NodoEstado {

    @Override
    public NodoEstado construir(Edificio construccion) {
        return new NodoOcupado(construccion);
    }
}
