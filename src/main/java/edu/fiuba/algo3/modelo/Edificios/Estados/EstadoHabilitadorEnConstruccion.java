package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;

import java.util.ArrayList;

public class EstadoHabilitadorEnConstruccion implements EstadoHabilitador {

    private int turnoParaEstarConstruido;

    public EstadoHabilitadorEnConstruccion(int turnoParaEstarConstruido){
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    @Override
    public EstadoHabilitador actualizar(ArrayList<Fabrica> fabricasAHabilitar, ArrayList<Fabrica> listaDeFabricasDisponibles) {
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0) {
            if (listaDeFabricasDisponibles != null)
                listaDeFabricasDisponibles.addAll(fabricasAHabilitar);

            return new EstadoHabilitadorConstruido();
        }
        return this;
    }
}
