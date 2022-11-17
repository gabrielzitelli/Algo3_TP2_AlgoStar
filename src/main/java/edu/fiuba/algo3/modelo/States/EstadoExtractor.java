package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.LinkedList;

public interface EstadoExtractor {

    public abstract EstadoExtractor actualizar();
    public abstract void extraer(Recurso gasDelImperio, MaterialBruto volcanDeGas, int vecesAExtraer);
    public abstract void contratarZangano(Unidad zanganoAContratar, LinkedList<Unidad> zanganosEmpleados);
}
