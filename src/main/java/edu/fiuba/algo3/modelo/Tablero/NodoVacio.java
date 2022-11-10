package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Zangano;

public class NodoVacio implements NodoEstado {

    @Override
    public NodoEstado construir(Edificio construccion) {
        return new NodoOcupado(construccion);
    }

    @Override
    public NodoEstado asignarUnidad(Zangano zangano) {
        return new NodoOcupado(zangano);
    }

    @Override
    public Terreno actualizar(Terreno unTerreno, Terreno conTerreno) {
        return unTerreno.actualizarTerreno(conTerreno);
    }

    @Override
    public NodoEstado desocuparNodo() {
        return this;
    }
}
