package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.FabricaScout;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;

import java.util.ArrayList;

public class EstadoPuertoEstelarEnConstruccion implements EstadoPuertoEstelar {

    private int turnoParaEstarConstruido;

    public EstadoPuertoEstelarEnConstruccion(int turnoParaEstarConstruido) {
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    public EstadoPuertoEstelar actualizar(ArrayList<Fabrica> listaDeFabricasDisponibles) {
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0) {
            if (listaDeFabricasDisponibles != null)
                listaDeFabricasDisponibles.add(new FabricaScout());

            return new EstadoPuertoEstelarConstruida();
        }
        return this;
    }

    public FabricaScout crearFabricaScout() {
        throw new ErrorEdificioNoEstaConstruido();
    }
}
