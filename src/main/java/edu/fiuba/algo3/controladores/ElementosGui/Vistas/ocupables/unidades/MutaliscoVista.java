package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Mutalisco;

public class MutaliscoVista extends OcupableVista {
    public MutaliscoVista() {
        this.tile = new Tile("unidades_zerg/32px/mutalisco.png");
        this.elemento = new Mutalisco();
        this.info = "Mutalisco";
    }
}
