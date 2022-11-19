package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;

// Permite crear unidades
public interface EstadoCreador {

    EstadoCreador actualizar();

    void crearUnidad(Fabrica unaFabrica, ArrayList<Unidad> unidades);
}
