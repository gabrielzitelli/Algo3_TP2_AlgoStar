package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;

public class GestorDeCrianza {

    ArrayList<Unidad> listaDeCrianza = new ArrayList<>();
    ArrayList<Unidad> unidadesDelImperio;

    public void agregarUnidad(Unidad unidad, ArrayList<Unidad> unidades) {
        if (unidadesDelImperio == null)
            unidadesDelImperio = unidades;

        listaDeCrianza.add(unidad);
    }

    public void actualizar() {
        ArrayList<Unidad> unidadesARemover = new ArrayList<>();

        for (Unidad unidad : listaDeCrianza){
            unidad.pasarTurno();
            if (unidad.estaConstruida() && unidadesDelImperio != null){
                unidadesDelImperio.add(unidad);
                unidadesARemover.add(unidad);
            }
        }

        for (Unidad unidad : unidadesARemover)
            listaDeCrianza.remove(unidad);
    }
}
