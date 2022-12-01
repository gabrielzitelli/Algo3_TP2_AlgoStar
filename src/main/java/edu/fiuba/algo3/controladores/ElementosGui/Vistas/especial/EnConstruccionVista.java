package edu.fiuba.algo3.controladores.ElementosGui.Vistas.especial;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.modelo.Edificios.EnConstruccion;

public class EnConstruccionVista extends EspecialVista {
    public EnConstruccionVista() {
        this.tile = new Tile("especial/construccion.png");
        this.elemento = new EnConstruccion();
        this.info = "En construccion";
    }
}
