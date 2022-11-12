package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.GasBruto;
import edu.fiuba.algo3.modelo.UnidadesZerg.Zangano;

import java.util.LinkedList;

public interface EstadoExtractor {
    public abstract EstadoExtractor actualizar();
    public abstract void extraer(Recurso gasDelImperio, GasBruto volcanDeGas, int vecesAExtraer);
    public abstract void contratarZangano(Zangano zanganoAContratar, LinkedList<Zangano> zanganosEmpleados);
}
