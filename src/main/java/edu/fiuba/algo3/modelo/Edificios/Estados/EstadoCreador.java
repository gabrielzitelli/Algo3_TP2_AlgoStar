package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;

// Permite crear unidades
public abstract class EstadoCreador {

    protected FabricasDisponibles fabricasDisponibles;

    public abstract EstadoCreador actualizar();

    public void asignarFabricasDisponibles(FabricasDisponibles fabricasDisponibles) {
        this.fabricasDisponibles = fabricasDisponibles;
    }

    public abstract void crearUnidad(Fabrica unaFabrica, ArrayList<Unidad> unidades);
}
