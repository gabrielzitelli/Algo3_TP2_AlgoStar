package edu.fiuba.algo3.controladores.ElementosGui.Vistas.moho;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.modelo.Mapa.Casilla.ConMoho;

public class ConMohoVista extends MohoVista {
    public ConMohoVista(){
        this.tile = new Tile("conMoho.png");
        this.info = "Moho Zerg";
        this.elemento = new ConMoho();
    }
}
