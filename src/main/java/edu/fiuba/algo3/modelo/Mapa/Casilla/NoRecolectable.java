package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Mapa.SinMaterialBruto;

public class NoRecolectable extends Recolectable{

    public NoRecolectable(){
        materialBruto = new SinMaterialBruto();
        this.identificador = "ninguno";
    }
}
