package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioProtoss.Energia;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;

public class EstadoPilonConstruido implements EstadoPilon {
    private Energia energia = new Energia();

    public EstadoPilonConstruido(Coordenada coordenada){
        energia.energizar(coordenada);
    }

    @Override
    public EstadoPilon actualizar(Coordenada coordenada) {
        return this;
    }
}
