package edu.fiuba.algo3.modelo.Edificios.Fabricas;

import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.UnidadZerg;

import java.util.ArrayList;

public class GestorDeCrianza {

    private Coordenada coordenadaEdificioCreador;
    private ArrayList<Unidad> listaDeCrianza = new ArrayList<>();
    private ArrayList<Unidad> unidadesDelImperio;

    private Mineral mineralDelImperio;

    public GestorDeCrianza(Coordenada coordenadaEdificioCreador){
        this.coordenadaEdificioCreador = coordenadaEdificioCreador;
    }

    public void agregarUnidad(Unidad unidad, ArrayList<Unidad> unidades, Mineral mineralDelImperio) {
        if (this.mineralDelImperio == null)
            this.mineralDelImperio = mineralDelImperio;

        if (unidadesDelImperio == null)
            unidadesDelImperio = unidades;

        listaDeCrianza.add(unidad);
    }

    public void actualizar() {
        ArrayList<Unidad> unidadesARemover = new ArrayList<>();
        Mapa elMapa = Mapa.obtener();

        for (Unidad unidad : listaDeCrianza){
            unidad.pasarTurno();
            if (unidad.estaConstruida() && unidadesDelImperio != null){
                if (unidad.perteneceAImperio(new Zerg()))
                    ((UnidadZerg) unidad).setDepositoRecurso(mineralDelImperio);
                unidadesDelImperio.add(unidad);
                elMapa.colocarUnidadEnCasillaLibreMasCercana(coordenadaEdificioCreador, unidad);
                unidadesARemover.add(unidad);
            }
        }

        for (Unidad unidad : unidadesARemover)
            listaDeCrianza.remove(unidad);
    }

    public void actualizarCoordenada(Coordenada coordenadaEdificioCreador) {
        this.coordenadaEdificioCreador = coordenadaEdificioCreador;
    }
}
