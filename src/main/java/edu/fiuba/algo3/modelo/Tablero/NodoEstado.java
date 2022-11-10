package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Zangano;

public interface NodoEstado {
    NodoEstado construir(Edificio construccion);

    NodoEstado asignarUnidad(Zangano zangano);
}
