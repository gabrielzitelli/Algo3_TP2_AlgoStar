package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.NexoMineral;
import edu.fiuba.algo3.modelo.Imperio.Mineral;

public class NexoMineralVista extends OcupableVista {
    public NexoMineralVista() {
        this.tile = new Tile("edificios_protoss/32px/nexo_mineral.png");
        this.elemento = new NexoMineral(new Mineral(0));
        this.info = "Nexo Mineral";
    }
}
