package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.MineralBruto;

public class EstadoNexoMineralConstruido implements EstadoNexoMineral{

    private int cantidadDeExtraccionUnitaria = 10;

    public EstadoNexoMineral actualizar(){
        return this;
    }

    public void extraer(Recurso mineralesDelImperio, MineralBruto nodoMineral){
        mineralesDelImperio.depositar(nodoMineral.extraer(cantidadDeExtraccionUnitaria));
    }
}
