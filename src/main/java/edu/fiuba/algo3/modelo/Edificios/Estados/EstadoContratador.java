package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.LinkedList;

// Permite contratar unidades (actualmente solo utilizado en el Extractor)
public interface EstadoContratador {

    EstadoContratador actualizar();

    void contratar(Unidad unidadAContratar, LinkedList<Unidad> unidadesEmpleados);

    void desContratar(LinkedList<Unidad> unidadesEmpleados);
}
