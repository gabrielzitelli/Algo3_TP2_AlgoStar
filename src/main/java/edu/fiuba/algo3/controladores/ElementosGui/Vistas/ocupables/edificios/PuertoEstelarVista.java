package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.PuertoEstelar;

public class PuertoEstelarVista extends OcupableVista {
    public PuertoEstelarVista() {
        this.tile = new Tile("edificios_protoss/32px/puerto_estelar.png");
        this.identificador = "puerto_estelar";
        this.info = "Puerto estelar";
    }
}
