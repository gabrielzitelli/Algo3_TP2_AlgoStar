package edu.fiuba.algo3.controladores.ElementosGui.Vistas.recursos;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.modelo.Mapa.GasBruto;

public class GasBrutoVista extends RecursoVista {
    public GasBrutoVista() {
        this.elemento = new GasBruto();
        this.tile = new Tile("recursos/32px/volcan.png");
        this.info = "Volcan de Gas Vespeno";
    }
}
