package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.LinkedList;

public class EstadoEdificioContratadorEnConstruccion implements EstadoEdificioContratador {

    private int turnoParaEstarConstruido;

    public EstadoEdificioContratadorEnConstruccion(int turnoParaEstarConstruido) {
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    @Override
    public EstadoEdificioContratador actualizar() {
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0)
            return new EstadoEdificioContratadorConstruido();

        return this;
    }

    @Override
    public void contratar(Unidad unidadAContratar, LinkedList<Unidad> unidadesEmpleados) {
        throw new ErrorEdificioNoEstaConstruido();
    }
}
