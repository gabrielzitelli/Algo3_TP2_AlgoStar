package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;

public abstract class FabricaEdificio {

    protected FabricasDisponibles fabricasDisponibles;
    protected ArrayList<Unidad> unidades;
    protected Mineral mineralesDelImperio;
    protected Gas gasDelImperio;

    public void asignar(FabricasDisponibles fabricasDisponibles, ArrayList<Unidad> unidades, Mineral mineralesDelImperio, Gas gasDelImperio) {
        this.fabricasDisponibles = fabricasDisponibles;
        this.unidades = unidades;
        this.mineralesDelImperio = mineralesDelImperio;
        this.gasDelImperio = gasDelImperio;
    }

    public abstract Edificio crear();
}
