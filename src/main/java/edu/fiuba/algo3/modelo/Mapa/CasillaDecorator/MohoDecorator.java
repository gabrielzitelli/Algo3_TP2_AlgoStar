package edu.fiuba.algo3.modelo.Mapa.CasillaDecorator;

import java.util.LinkedList;

public class MohoDecorator extends SuperficieDecorator{
    private CasillaDecorator unaCasilla;

    public MohoDecorator(CasillaDecorator unaCasilla){
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
