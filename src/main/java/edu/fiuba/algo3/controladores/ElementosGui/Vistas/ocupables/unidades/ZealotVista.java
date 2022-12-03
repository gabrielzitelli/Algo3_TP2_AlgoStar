package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;

public class ZealotVista extends OcupableVista {

    public ZealotVista(){
        this.tile = new Tile("unidades_protoss/32px/zealot.png");
        this.identificador = "zealot";
        this.info = "Zealot";
    }
}
