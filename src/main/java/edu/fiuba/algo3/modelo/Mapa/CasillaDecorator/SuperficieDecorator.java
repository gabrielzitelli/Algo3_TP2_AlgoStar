package edu.fiuba.algo3.modelo.Mapa.CasillaDecorator;

import java.util.LinkedList;

public abstract class SuperficieDecorator extends CasillaDecorator{
    public abstract Boolean tenesEstosDecoradores(LinkedList<CasillaDecorator> casillas);
}
