package edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Mapa;

public abstract class EdificioConCarga extends Edificio {
    public boolean verificarCarga() {
        Mapa elMapa = Mapa.obtener();
        if (coordenada == null) {
            return true;
        }
        return elMapa.estaEnergizado(coordenada);
    }
}
