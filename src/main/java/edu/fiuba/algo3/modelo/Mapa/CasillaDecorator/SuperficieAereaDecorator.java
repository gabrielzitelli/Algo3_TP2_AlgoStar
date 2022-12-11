package edu.fiuba.algo3.modelo.Mapa.CasillaDecorator;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;

import java.util.LinkedList;

public class SuperficieAereaDecorator extends CasillaDecorator{
    private CasillaDecorator unaCasilla;

    public SuperficieAereaDecorator(CasillaDecorator unaCasilla){
        this.unaCasilla = unaCasilla;
    }

    @Override
    public Boolean tenesEstosDecoradores(LinkedList<CasillaDecorator> casillas) {
        return false;
    }

    @Override
    public Coordenada obtenerCoordenada() {
        return this.unaCasilla.obtenerCoordenada();
    }
}
