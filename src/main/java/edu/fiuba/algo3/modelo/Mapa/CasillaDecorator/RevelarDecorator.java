package edu.fiuba.algo3.modelo.Mapa.CasillaDecorator;

import java.util.LinkedList;

public class RevelarDecorator extends CasillaDecorator{
    private CasillaDecorator unaCasilla;

    public RevelarDecorator(CasillaDecorator unaCasilla){
        this.unaCasilla = unaCasilla;
    }

    @Override
    public Boolean tenesEstosDecoradores(LinkedList<CasillaDecorator> casillas) {
        for(CasillaDecorator decorador : casillas){

        }
        return false;
    }
}
