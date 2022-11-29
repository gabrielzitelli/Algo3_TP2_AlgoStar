package edu.fiuba.algo3.controladores.ElementosGui.Vistas.superficie;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;

public class SuperficieTerrestreVista extends SuperficieVista {

    public SuperficieTerrestreVista(){
        this.elemento = new SuperficieTerrestre();
        this.tile = new Tile("tierra.png");
        this.info = "Superficie";
    }
}
