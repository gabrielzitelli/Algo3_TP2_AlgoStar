package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;

public class EstadoCreadorEnConstruccion implements EstadoCreador {

    private int turnoParaEstarConstruido;
    private FabricasDisponibles fabricasDisponibles;

    public EstadoCreadorEnConstruccion(int turnoParaEstarConstruido) {
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    @Override
    public EstadoCreador actualizar() {
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0)
            return new EstadoCreadorConstruido(fabricasDisponibles);

        return this;
    }

    @Override
    public void asignarFabricasDisponibles(FabricasDisponibles fabricasDisponibles) {
        this.fabricasDisponibles = fabricasDisponibles;
    }

    @Override
    public void crearUnidad(Fabrica unaFabrica, ArrayList<Unidad> unidades) {
        throw new ErrorEdificioNoEstaConstruido();
    }
}
