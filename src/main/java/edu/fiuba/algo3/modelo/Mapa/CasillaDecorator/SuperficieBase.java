package edu.fiuba.algo3.modelo.Mapa.CasillaDecorator;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;

import java.util.LinkedList;

public class SuperficieBase extends CasillaDecorator{

    public SuperficieBase(Coordenada unaCoordenada){
        this.unaCoordenada = unaCoordenada;
    }
    @Override
    public Boolean tenesEstosDecoradores(LinkedList<CasillaDecorator> casillas) {
        return true;
    }

    public Coordenada obtenerCoordenada(){
        return this.unaCoordenada;
    }
}
