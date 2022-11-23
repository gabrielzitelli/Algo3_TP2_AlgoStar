package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Imperio.Suministro;

import java.util.ArrayList;

public class EstadoHabilitadorConstruido implements EstadoHabilitador {

    @Override
    public EstadoHabilitador actualizar(ArrayList<Fabrica> fabricasAHabilitar, FabricasDisponibles fabricasDisponibles) {
        return this;
    }

    @Override
    public void marcarSuministro(Suministro suministroImperio, int cantidadAumentoSuministro) {
    }

    @Override
    public void modificarSuministro(){

    }
}
