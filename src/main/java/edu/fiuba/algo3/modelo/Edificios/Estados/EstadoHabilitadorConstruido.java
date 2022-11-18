package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;

import java.util.ArrayList;

public class EstadoHabilitadorConstruido implements EstadoHabilitador {

    @Override
    public EstadoHabilitador actualizar(ArrayList<Fabrica> fabricasAHabilitar, ArrayList<Fabrica> listaDeFabricasDisponibles) {
        return this;
    }
}
