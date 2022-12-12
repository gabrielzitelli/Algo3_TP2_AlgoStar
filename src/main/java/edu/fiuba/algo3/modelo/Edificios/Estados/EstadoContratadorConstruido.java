package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Excepciones.ErrorExtractorNoPuedeDescontratarUnidadesSiNoContrataPrimero;
import edu.fiuba.algo3.modelo.Excepciones.ErrorExtractorNoPuedeTenerMasDe3ZanganosAlMismoTiempo;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.LinkedList;

public class EstadoContratadorConstruido implements EstadoContratador {

    @Override
    public EstadoContratador actualizar() {
        return this;
    }

    @Override
    public void contratar(Unidad unidadAContratar, LinkedList<Unidad> unidadesEmpleados) {
        if (unidadesEmpleados.size() >= 3)
            throw new ErrorExtractorNoPuedeTenerMasDe3ZanganosAlMismoTiempo();

        unidadesEmpleados.add(unidadAContratar);
    }

    @Override
    public void desContratar(LinkedList<Unidad> unidadesEmpleados) {
        if (unidadesEmpleados.size() <= 0)
            throw new ErrorExtractorNoPuedeDescontratarUnidadesSiNoContrataPrimero();

        Unidad zangano = unidadesEmpleados.removeLast();
        Mapa.obtener().colocarUnidadEnCasillaLibreMasCercana(zangano.obtenerCoordenada(), zangano);
    }
}
