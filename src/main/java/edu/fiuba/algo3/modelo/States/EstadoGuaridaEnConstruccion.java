package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;
import edu.fiuba.algo3.modelo.EdificioZerg.FabricaHidralisco;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;

import java.util.ArrayList;

public class EstadoGuaridaEnConstruccion implements EstadoGuarida {

    private int turnoParaEstarConstruido;

    public EstadoGuaridaEnConstruccion(int turnoParaEstarConstruido) {
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    public FabricaHidralisco crearFabricaHidralisco(){
        throw new ErrorEdificioNoEstaConstruido();
    }

    public EstadoGuarida actualizar(ArrayList<Fabrica> listaDeFabricasDisponibles){
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0) {
            if (listaDeFabricasDisponibles != null)
                listaDeFabricasDisponibles.add(new FabricaHidralisco());
            return new EstadoGuaridaConstruida();
        }

        return this;
    }
}
