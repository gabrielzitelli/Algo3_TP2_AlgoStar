package edu.fiuba.algo3.modelo.Mapa.CasillaDecorator;


import edu.fiuba.algo3.modelo.Mapa.Coordenada;

import java.util.LinkedList;

public abstract class CasillaDecorator {
    protected Coordenada unaCoordenada;
    public abstract Boolean tenesEstosDecoradores(LinkedList<CasillaDecorator> casillas);
    public abstract Coordenada obtenerCoordenada();
}
