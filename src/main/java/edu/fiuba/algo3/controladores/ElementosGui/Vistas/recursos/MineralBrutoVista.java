package edu.fiuba.algo3.controladores.ElementosGui.Vistas.recursos;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.modelo.Mapa.MineralBruto;

public class MineralBrutoVista extends RecursoVista {

    public MineralBrutoVista() {
        tile = new Tile("recursos/32px/mineral.png");
        identificador = "mineral";
        info = "Minerales";
    }
}
