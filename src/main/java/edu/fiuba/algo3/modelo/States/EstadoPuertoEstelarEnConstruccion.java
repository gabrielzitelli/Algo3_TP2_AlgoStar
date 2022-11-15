package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.EdificioProtoss.FabricaScout;
import edu.fiuba.algo3.modelo.EdificioZerg.Fabrica;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;

import java.util.ArrayList;

public class EstadoPuertoEstelarEnConstruccion implements EstadoPuertoEstelar {
    private int turnoParaEstarConstruido;
    public EstadoPuertoEstelarEnConstruccion(int turnoParaEstarConstruido) {
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    @Override
    public EstadoPuertoEstelar actualizar(ArrayList<Fabrica> listaDeFabricasDisponibles) {
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0) {
            if (listaDeFabricasDisponibles != null)
                listaDeFabricasDisponibles.add(new FabricaScout());
            return new EstadoPuertoEstelarConstruida();
        }
        return this;
    }

    @Override
    public FabricaScout crearFabricaScout() {
        throw new ErrorEdificioNoEstaConstruido();
    }
}
