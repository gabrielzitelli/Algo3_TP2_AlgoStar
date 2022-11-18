package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.LinkedList;

public interface EstadoEdificioContratador {

    EstadoEdificioContratador actualizar();

    void contratar(Unidad unidadAContratar, LinkedList<Unidad> unidadesEmpleados);
}