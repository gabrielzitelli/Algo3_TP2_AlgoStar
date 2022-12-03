package edu.fiuba.algo3.controladores.ElementosGui.Vistas.moho;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.modelo.Mapa.Casilla.ConMoho;

public class ConMohoVista extends MohoVista {
    public ConMohoVista(){
        this.tile = new Tile("superficies/32px/moho_transparente.png");
        this.info = "Moho Zerg";
        this.identificador = "moho";
    }
}
