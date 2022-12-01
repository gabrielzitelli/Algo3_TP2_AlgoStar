package edu.fiuba.algo3.modelo.Ataque;

import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Imperio.Recurso;

import java.util.ArrayList;

public interface Ocupable {

    void recibirAtaque(Ataque unAtaque);

    ArrayList<Recurso> requisitosMateriales();

    boolean perteneceAImperio(Imperio imperio);

    Object obtenerEstado();
}
