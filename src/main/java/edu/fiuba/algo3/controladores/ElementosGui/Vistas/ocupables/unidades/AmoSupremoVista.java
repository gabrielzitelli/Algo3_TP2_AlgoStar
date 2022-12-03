package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.AmoSupremo;

public class AmoSupremoVista extends OcupableVista {
    public AmoSupremoVista() {
        this.tile = new Tile("unidades_zerg/32px/amo_supremo.png");
        this.identificador = "amo_supremo";
        this.info = "Amo Supremo";
    }
}
