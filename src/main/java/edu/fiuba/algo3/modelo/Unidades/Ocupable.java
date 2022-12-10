package edu.fiuba.algo3.modelo.Unidades;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;

import java.util.ArrayList;

public interface Ocupable {

    void recibirAtaque(Ataque unAtaque);

    ArrayList<Recurso> requisitosMateriales();

    boolean perteneceAImperio(Imperio imperio);

    void verificarColocable(Casilla unaCasilla);

    void actualizarColocable(Casilla unaCasilla);

    boolean esDeEsteTipo(Class claseAAverificar);

}
