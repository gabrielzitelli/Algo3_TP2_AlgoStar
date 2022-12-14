package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Mapa.GasBruto;

public class GasRecolectable extends Recolectable{

    public GasRecolectable(){
        materialBruto = new GasBruto();
        this.identificador = "gas";
    }
}
