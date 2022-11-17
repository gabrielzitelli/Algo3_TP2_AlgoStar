package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;

public class EstadoPilonEnConstruccion implements EstadoPilon {

    private int turnoParaEstarConstruido;

    public EstadoPilonEnConstruccion(int turnoParaEstarConstruido) {
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    public EstadoPilon actualizar(Coordenada coordenada) {
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0)
            return new EstadoPilonConstruido(coordenada);

        return this;
    }
}
