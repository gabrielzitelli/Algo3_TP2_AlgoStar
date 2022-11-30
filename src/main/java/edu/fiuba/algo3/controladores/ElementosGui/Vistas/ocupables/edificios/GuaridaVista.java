package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Guarida;

public class GuaridaVista extends OcupableVista {
    public GuaridaVista() {
        this.tile = new Tile("edificios_zerg/32px/guarida.png");
        this.elemento = new Guarida();
        this.info = "Guarida";
    }
}
