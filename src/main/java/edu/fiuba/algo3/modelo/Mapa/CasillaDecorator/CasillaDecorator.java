package edu.fiuba.algo3.modelo.Mapa.CasillaDecorator;


import java.util.LinkedList;

public abstract class CasillaDecorator {
    public abstract Boolean tenesEstosDecoradores(LinkedList<CasillaDecorator> casillas);
}
