package edu.fiuba.algo3.modelo.Mapa.CasillaDecorator;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Unidades.Ocupable;

import java.util.LinkedList;

public class OcupableDecorator extends CasillaDecorator{
    private Ocupable unOcupable;
    private CasillaDecorator unaCasilla;

    public OcupableDecorator(Ocupable unOcupable, CasillaDecorator unaCasilla){
        this.unaCasilla = unaCasilla;
        this.unOcupable = unOcupable;
    }

    @Override
    public Boolean tenesEstosDecoradores(LinkedList<CasillaDecorator> casillas) {
        /*for(CasillaDecorator decorador : casillas){
            if (this.getClass().equals(decorador.getClass())){
                return this.unaCasilla.tenesEstosDecoradores(casillas);
            }
        }
        return false;*/
        throw new ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada();
    }

    @Override
    public Coordenada obtenerCoordenada() {
        return this.unaCasilla.obtenerCoordenada();
    }
}
