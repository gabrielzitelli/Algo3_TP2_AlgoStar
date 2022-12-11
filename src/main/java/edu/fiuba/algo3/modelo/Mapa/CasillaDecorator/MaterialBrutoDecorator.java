package edu.fiuba.algo3.modelo.Mapa.CasillaDecorator;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;

import java.util.LinkedList;

public class MaterialBrutoDecorator extends CasillaDecorator{

    private CasillaDecorator unaCasilla;
    private MaterialBruto unMaterialBruto;

    public MaterialBrutoDecorator(MaterialBruto unMaterialBruto, CasillaDecorator unaCasilla){
        this.unaCasilla = unaCasilla;
        this.unMaterialBruto = unMaterialBruto;
    }
    @Override
    public Boolean tenesEstosDecoradores(LinkedList<CasillaDecorator> casillas) {
        // TODO Estoy cansado, pensar si esto deberia hacer este for realmente u otra cosa
        for(CasillaDecorator decorador : casillas){
            if (this.getClass().equals(decorador.getClass())){
                return this.unaCasilla.tenesEstosDecoradores(casillas);
            }
        }
        return false;
    }

    @Override
    public Coordenada obtenerCoordenada() {
        return this.unaCasilla.obtenerCoordenada();
    }
}
