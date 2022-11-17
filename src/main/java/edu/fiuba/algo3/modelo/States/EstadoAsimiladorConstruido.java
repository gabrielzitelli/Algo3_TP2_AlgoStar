package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;

public class EstadoAsimiladorConstruido implements EstadoAsimilador{

    private int cantidadDeExtraccionUnitaria = 20;

    public EstadoAsimilador actualizar(){
        return this;
    }

    public void extraer(Recurso gasDelImperio, MaterialBruto volcanDeGas){
        if (volcanDeGas != null)
            gasDelImperio.depositar(volcanDeGas.extraer(cantidadDeExtraccionUnitaria));
    }
}
