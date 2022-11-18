package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Excepciones.ErrorExtratorNoPuedeTenerMasDe3ZanganosAlMismoTiempo;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.LinkedList;

public class EstadoEdificioContratadorConstruido implements EstadoEdificioContratador {

    @Override
    public EstadoEdificioContratador actualizar() {
        return this;
    }

    @Override
    public void contratar(Unidad unidadAContratar, LinkedList<Unidad> unidadesEmpleados) {
        if (unidadesEmpleados.size() >= 3)
            throw new ErrorExtratorNoPuedeTenerMasDe3ZanganosAlMismoTiempo();

        unidadesEmpleados.add(unidadAContratar);
    }
}
