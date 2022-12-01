package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Devorador;

public class DevoradorVista extends OcupableVista {
    public DevoradorVista(){
        this.tile = new Tile("unidades_zerg/32px/devorador.png");
        this.elemento = new Devorador();
        this.info = "Devorador";
    }
}