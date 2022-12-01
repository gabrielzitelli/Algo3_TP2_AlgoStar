package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Mapa.Casilla.ConRevelar;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;

public class GestorDeCrianza {

    private Coordenada coordenadaEdificioCreador;
    private ArrayList<Unidad> listaDeCrianza = new ArrayList<>();
    private ArrayList<Unidad> unidadesDelImperio;

    public GestorDeCrianza(Coordenada coordenadaEdificioCreador){
        this.coordenadaEdificioCreador = coordenadaEdificioCreador;
    }

    public void agregarUnidad(Unidad unidad, ArrayList<Unidad> unidades) {
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
