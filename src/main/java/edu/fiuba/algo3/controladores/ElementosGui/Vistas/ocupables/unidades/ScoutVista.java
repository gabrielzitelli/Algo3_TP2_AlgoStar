package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Scout;

public class ScoutVista extends OcupableVista {

    public ScoutVista() {
        this.tile = new Tile("unidades_protoss/32px/scout.png");
        this.elemento = new Scout();
        this.info = "Scout";
    }
}
