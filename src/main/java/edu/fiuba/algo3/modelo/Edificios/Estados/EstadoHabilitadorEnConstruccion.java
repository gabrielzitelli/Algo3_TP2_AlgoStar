package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;

import java.util.ArrayList;

public class EstadoHabilitadorEnConstruccion implements EstadoHabilitador {

    private int turnoParaEstarConstruido;

    public EstadoHabilitadorEnConstruccion(int turnoParaEstarConstruido){
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    @Override
    public EstadoHabilitador actualizar(ArrayList<Fabrica> fabricasAHabilitar, FabricasDisponibles fabricasDisponibles) {
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0) {
            if (fabricasDisponibles != null)
                fabricasDisponibles.aumentar(fabricasAHabilitar);

            return new EstadoHabilitadorConstruido();
        }
        return this;
    }
}
