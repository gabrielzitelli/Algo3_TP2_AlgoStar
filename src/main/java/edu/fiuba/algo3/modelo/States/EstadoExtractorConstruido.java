package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeHaConstruidoElExtractorSobreUnaCasilla;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeExtraerSinZanganoAsignado;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.GasBruto;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.UnidadesZerg.Zangano;

import java.util.LinkedList;

public class EstadoExtractorConstruido implements EstadoExtractor{

    private int cantidadDeExtraccionUnitaria = 10;

    public EstadoExtractor actualizar(){
        return this;
    }

    public void extraer(Recurso gasDelImperio, MaterialBruto volcanDeGas, int vecesAExtraer){
        if(vecesAExtraer == 0)
            throw new ErrorNoSePuedeExtraerSinZanganoAsignado();

        if(volcanDeGas == null)
            throw new ErrorNoSeHaConstruidoElExtractorSobreUnaCasilla();

        for(int i = 0; i < vecesAExtraer; i++)
            gasDelImperio.depositar(volcanDeGas.extraer(cantidadDeExtraccionUnitaria));
    }

    public void contratarZangano(Zangano zanganoAContratar, LinkedList<Zangano> zanganosEmpleados){
        zanganosEmpleados.add(zanganoAContratar);
    }
}
