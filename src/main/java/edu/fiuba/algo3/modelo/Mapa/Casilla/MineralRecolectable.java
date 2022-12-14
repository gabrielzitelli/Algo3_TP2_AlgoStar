package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Mapa.MineralBruto;

public class MineralRecolectable extends Recolectable{

    public MineralRecolectable(){
        materialBruto = new MineralBruto();
        this.identificador = "mineral";
    }
}
