package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;

public class DevoradorVista extends UnidadZergVista {
    public DevoradorVista(){
        this.tile = new Tile("unidades_zerg/32px/devorador.png");
        this.identificador = "devorador";
        this.info = "Devorador";
    }
}
