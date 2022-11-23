package edu.fiuba.algo3.modelo.Unidades;

import edu.fiuba.algo3.modelo.Ataque.Ocupable;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Superficie;
import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;

import java.util.ArrayList;

public interface Unidad extends Ocupable {

     void verificarSuperficie(Superficie superficie);
     int rangoDeAtaque();
     boolean estaConstruida();
     void pasarTurno();
     Ataque atacar();
     boolean esIgualA(Unidad unidad);
     ArrayList<Recurso> requisitosMateriales();
}
