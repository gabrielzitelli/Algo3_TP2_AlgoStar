package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Extractor;
import edu.fiuba.algo3.modelo.Imperio.Gas;

public class ExtractorVista extends OcupableVista {
    public ExtractorVista() {
        this.tile = new Tile("edificios_zerg/32px/extractor.png");
        this.identificador = "extractor";
        this.info = "Extractor";
    }
}
