package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Pilon;

public class PilonVista extends OcupableVista {
    public PilonVista() {
        this.tile = new Tile("edificios_protoss/32px/pilon.png");
        this.elemento = new Pilon();
        this.info = "Pilon";
    }
}
