package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Excepciones.ErrorExtratorNoPuedeTenerMasDe3ZanganosAlMismoTiempo;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.LinkedList;

public class EstadoExtractorConstruido implements EstadoExtractor{

    private int cantidadDeExtraccionUnitaria = 10;

    public EstadoExtractor actualizar(){
        return this;
    }

    public void extraer(Recurso gasDelImperio, MaterialBruto volcanDeGas, int vecesAExtraer){
        if (volcanDeGas != null) {
            for (int i = 0; i < vecesAExtraer; i++)
                gasDelImperio.depositar(volcanDeGas.extraer(cantidadDeExtraccionUnitaria));
        }
    }

    public void contratarZangano(Unidad zanganoAContratar, LinkedList<Unidad> zanganosEmpleados){
        if (zanganosEmpleados.size() >= 3)
            throw new ErrorExtratorNoPuedeTenerMasDe3ZanganosAlMismoTiempo();

        zanganosEmpleados.add(zanganoAContratar);
    }
}
