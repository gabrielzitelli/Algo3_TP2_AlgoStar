package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Asimilador;
import edu.fiuba.algo3.modelo.Imperio.Gas;

public class AsimiladorVista extends OcupableVista {
    public AsimiladorVista() {
        this.tile = new Tile("edificios_protoss/32px/asimilador.png");
        this.elemento = new Asimilador(new Gas(0));
        this.info = "Asimilador";
    }
}
