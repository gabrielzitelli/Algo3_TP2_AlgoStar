package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.UnidadesZerg.UnidadZerg;

import java.util.LinkedList;

public class EstadoExtractorEnConstruccion implements EstadoExtractor{
    private int turnoParaEstarConstruido;

    public EstadoExtractorEnConstruccion(int turnoParaEstarConstruido){
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    public EstadoExtractor actualizar(){
            turnoParaEstarConstruido--;
            if(turnoParaEstarConstruido == 0)
                return new EstadoExtractorConstruido();

            return this;
    }

    public void extraer(Recurso gasDelImperio, MaterialBruto volcanDeGas, int vecesAExtraer){
    }

    public void contratarZangano(UnidadZerg zanganoAContratar, LinkedList<UnidadZerg> zanganosEmpleados){
        throw new ErrorEdificioNoEstaConstruido();
    }
}
