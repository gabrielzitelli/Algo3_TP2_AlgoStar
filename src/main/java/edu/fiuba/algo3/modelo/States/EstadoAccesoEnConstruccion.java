package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.FabricaDragon;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.FabricaZealot;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;

public class EstadoAccesoEnConstruccion implements EstadoAcceso {

    private int turnoParaEstarConstruido;

    public EstadoAccesoEnConstruccion(int turnoParaEstarConstruido) {
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    public FabricaDragon crearFabricaDragon(){
        throw new ErrorEdificioNoEstaConstruido();
    }

    public FabricaZealot crearFabricaZealot(){
        throw new ErrorEdificioNoEstaConstruido();
    }

    public void crearUnidad(Fabrica unaFabrica, ArrayList<Unidad> unidades) {
        throw new ErrorEdificioNoEstaConstruido();
    }

    public EstadoAcceso actualizar(){
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0)
            return new EstadoAccesoConstruido();

        return this;
    }
}
