package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;

public class DragonVista extends OcupableVista {

    public DragonVista() {
        this.tile = new Tile("unidades_protoss/32px/dragon.png");
        this.elemento = new Dragon();
        this.info = "Dragon";
    }
}
