package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Edificio;

public interface NodoEstado {
    NodoEstado construir(Edificio construccion);
}
