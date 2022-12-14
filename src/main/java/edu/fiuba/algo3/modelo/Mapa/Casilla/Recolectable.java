package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;

public abstract class Recolectable {

    protected String identificador;
    protected MaterialBruto materialBruto;

    public boolean soyDiferenteA(Recolectable recolectableRequerido){
        return !this.getClass().equals(recolectableRequerido.getClass());
    }

    public MaterialBruto obtenerMaterial(){
        return materialBruto;
    }

    @Override
    public String toString() {
        return identificador;
    }
}
