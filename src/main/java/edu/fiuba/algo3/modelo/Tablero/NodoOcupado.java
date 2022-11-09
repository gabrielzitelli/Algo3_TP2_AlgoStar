package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.excepciones.CasillaOcupada;

public class NodoOcupado implements NodoEstado {
    Edificio edificioQueOcupa;
    public NodoOcupado(Edificio construccion){
        edificioQueOcupa = construccion;
    }
    @Override
    public NodoEstado construir(Edificio construccion) {
        throw new CasillaOcupada();
    }
}
