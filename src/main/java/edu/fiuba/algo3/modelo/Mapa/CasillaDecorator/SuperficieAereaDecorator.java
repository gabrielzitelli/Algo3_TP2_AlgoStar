package edu.fiuba.algo3.modelo.Mapa.CasillaDecorator;

import java.util.LinkedList;

public class SuperficieAereaDecorator extends CasillaDecorator{
    private CasillaDecorator unaCasilla;

    public SuperficieAereaDecorator(CasillaDecorator unaCasilla){
        this.unaCasilla = unaCasilla;
    }

    @Override
    public Boolean tenesEstosDecoradores(LinkedList<CasillaDecorator> casillas) {
        for(CasillaDecorator decorador : casillas){

        }
        return false;
    }
}
