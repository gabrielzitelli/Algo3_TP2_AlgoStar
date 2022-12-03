package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Espiral;

public class EspiralVista extends OcupableVista {

    public EspiralVista(){
        this.tile = new Tile("edificios_zerg/32px/espiral.png");
        this.identificador = "espiral";
        this.info = "Espiral";
    }
}
