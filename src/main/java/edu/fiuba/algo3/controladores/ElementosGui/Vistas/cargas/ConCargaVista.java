package edu.fiuba.algo3.controladores.ElementosGui.Vistas.cargas;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.modelo.Mapa.Casilla.ConCarga;

public class ConCargaVista extends CargaVista {

    public ConCargaVista() {
        this.tile = new Tile("superficies/32px/energia_transparente.png");
        this.elemento = new ConCarga();
        this.info = "electrificado";
    }
}
