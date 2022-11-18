package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.GestorDeCrianza;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;

public class EstadoCreadorConstruido implements EstadoCreador {

    GestorDeCrianza gestorDeCrianza = new GestorDeCrianza();

    @Override
    public EstadoCreador actualizar() {
        gestorDeCrianza.actualizar();
        return this;
    }

    @Override
    public void crearUnidad(Fabrica unaFabrica, ArrayList<Unidad> unidades) {
        gestorDeCrianza.agregarUnidad(unaFabrica.crearUnidad(), unidades);
    }
}
