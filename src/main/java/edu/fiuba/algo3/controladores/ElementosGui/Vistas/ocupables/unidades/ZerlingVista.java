package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;

public class ZerlingVista extends OcupableVista {
    public ZerlingVista() {
        this.tile = new Tile("unidades_zerg/32px/zerling.png");
        this.elemento = new Zerling();
        this.info = "Zerling";
    }
}
