package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;

public class GuardianVista extends UnidadZergVista {
    public GuardianVista() {
        this.tile = new Tile("unidades_zerg/32px/guardian.png");
        this.identificador = "guardian";
        this.info = "Guardian";
    }
}
