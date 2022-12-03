package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Acceso;

public class AccesoVista extends OcupableVista {

    public AccesoVista() {
        this.tile = new Tile("edificios_protoss/32px/acceso.png");
        this.identificador = "acceso";
        this.info = "Acceso";
    }
}
