package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.Fabricas.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Unidades.Ocupable;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;

// Permite crear unidades
public abstract class EstadoCreador {

    protected FabricasDisponibles fabricasDisponibles;

    public abstract EstadoCreador actualizar();

    public void asignarFabricasDisponibles(FabricasDisponibles fabricasDisponibles) {
        this.fabricasDisponibles = fabricasDisponibles;
    }

    public abstract void verificarQueSePuedeFabricar(Fabrica unaFabrica);
    public abstract void comprobarRequisitosMaterialesVerificacion(Ocupable ocupable, Mineral mineralDelImperio, Gas gasDelImperio);

    public abstract void colocarCoordenadaAlGestorDeCrianza(Coordenada coordenadaEdificioCreador);

    public abstract void crearUnidad(Fabrica unaFabrica, ArrayList<Unidad> unidades, Mineral mineralDelImperio, Gas gasDelImperio);
}
