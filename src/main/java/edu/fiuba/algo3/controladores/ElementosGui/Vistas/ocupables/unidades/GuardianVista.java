package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Guardian;

public class GuardianVista extends OcupableVista {
    public GuardianVista() {
        this.tile = new Tile("unidades_zerg/32px/guardian.png");
        this.elemento = new Guardian();
        this.info = "Guardian";
    }
}