package edu.fiuba.algo3.controladores.ElementosGui.Vistas.superficie;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;

public class SuperficieTerrestreVista extends SuperficieVista {

    public SuperficieTerrestreVista(){
        this.identificador = "terrestre";
        this.tile = new Tile("superficies/32px/terrestre.png");
        this.info = "Superficie";
    }
}
