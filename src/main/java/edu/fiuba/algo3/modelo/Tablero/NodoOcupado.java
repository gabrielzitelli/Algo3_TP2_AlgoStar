package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Zangano;
import edu.fiuba.algo3.modelo.excepciones.CasillaOcupada;

public class NodoOcupado implements NodoEstado {
    private Edificio edificioQueOcupa;
    private Zangano unidadQueOcupa;

    public NodoOcupado(Edificio construccion){
        edificioQueOcupa = construccion;
    }

    public NodoOcupado(Zangano zangano) {
        unidadQueOcupa = zangano;
    }

    @Override
    public NodoEstado construir(Edificio construccion) {
        throw new CasillaOcupada();
    }

    @Override
    public NodoEstado asignarUnidad(Zangano zangano) {
        throw new CasillaOcupada();
    }

    @Override
    public Terreno actualizar(Terreno unTerreno, Terreno conTerreno) {
        return unTerreno;
    }

    @Override
    public NodoEstado desocuparNodo() {
        return new NodoVacio();
    }
}
