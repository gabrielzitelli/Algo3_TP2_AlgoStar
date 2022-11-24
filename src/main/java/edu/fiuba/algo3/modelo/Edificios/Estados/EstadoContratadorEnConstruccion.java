package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.LinkedList;

public class EstadoContratadorEnConstruccion implements EstadoContratador {

    private int turnoParaEstarConstruido;

    public EstadoContratadorEnConstruccion(int turnoParaEstarConstruido) {
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    @Override
    public EstadoContratador actualizar() {
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0)
            return new EstadoContratadorConstruido();

        return this;
    }

    @Override
    public void contratar(Unidad unidadAContratar, LinkedList<Unidad> unidadesEmpleados) {
        throw new ErrorEdificioNoEstaConstruido();
    }
}
