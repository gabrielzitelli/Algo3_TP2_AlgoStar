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
            if (this.getClass().equals(decorador.getClass())){
                return this.unaCasilla.tenesEstosDecoradores(casillas);
            }
        }
        return false;
    }
}
