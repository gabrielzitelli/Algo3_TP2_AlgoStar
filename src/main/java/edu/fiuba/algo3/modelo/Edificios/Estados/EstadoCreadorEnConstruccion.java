package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.Fabricas.Fabrica;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;

public class EstadoCreadorEnConstruccion extends EstadoCreador {

    private int turnoParaEstarConstruido;
    private Coordenada coordenadaEdificioCreador;

    public EstadoCreadorEnConstruccion(int turnoParaEstarConstruido, Coordenada coordenadaEdificioCreador) {
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
        this.coordenadaEdificioCreador = coordenadaEdificioCreador;
    }

    @Override
    public EstadoCreador actualizar() {
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0)
            return new EstadoCreadorConstruido(this.fabricasDisponibles, this.coordenadaEdificioCreador);

        return this;
    }

    public void colocarCoordenadaAlGestorDeCrianza(Coordenada coordenadaEdificioCreador){
        this.coordenadaEdificioCreador = coordenadaEdificioCreador;
    }

    @Override
    public void crearUnidad(Fabrica unaFabrica, ArrayList<Unidad> unidades, Mineral mineralDelImperio, Gas gasDelImperio) {
        throw new ErrorEdificioNoEstaConstruido();
    }
}
