package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;

public class ZanganoVista extends OcupableVista {
    public ZanganoVista() {
        this.tile = new Tile("unidades_zerg/32px/zangano.png");
        this.identificador = "zangano";
        this.info = "Zangano";
    }
}
