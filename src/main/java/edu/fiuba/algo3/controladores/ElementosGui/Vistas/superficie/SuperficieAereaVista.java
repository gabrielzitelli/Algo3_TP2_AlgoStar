package edu.fiuba.algo3.controladores.ElementosGui.Vistas.superficie;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;

public class SuperficieAereaVista extends SuperficieVista {
    public SuperficieAereaVista(){
        this.identificador = "aerea";
        this.tile = new Tile("superficies/32px/espacial.png");
        this.info = "Espacial";
    }
}
