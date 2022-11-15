package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;

public class EstadoAsimiladorEnConstruccion implements EstadoAsimilador{

    private int turnoParaEstarConstruido;

    public EstadoAsimiladorEnConstruccion(int turnoParaEstarConstruido){
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    public EstadoAsimilador actualizar(){
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0)
            return new EstadoAsimiladorConstruido();

        return this;
    }

    public void extraer(Recurso gasDelImperio, MaterialBruto volcanDeGas){
    }
}
