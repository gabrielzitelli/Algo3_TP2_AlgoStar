package edu.fiuba.algo3.controladores.ElementosGui.Vistas.especial;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;

public class EnConstruccionVista extends EspecialVista {
    public EnConstruccionVista() {
        this.tile = new Tile("especial/construccion.png");
        this.identificador = "en_construccion";
        this.info = "En construccion";
    }
}
