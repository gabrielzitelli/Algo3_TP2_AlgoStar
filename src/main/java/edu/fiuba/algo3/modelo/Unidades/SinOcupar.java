package edu.fiuba.algo3.modelo.Unidades;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Ataque.Ocupable;
import edu.fiuba.algo3.modelo.Excepciones.ErrorUnaCasillaVaciaNoPuedeParticiparEnAtaque;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;

import java.util.ArrayList;

public class SinOcupar implements Ocupable {
    @Override
    public void recibirAtaque(Ataque unAtaque) {
        throw new ErrorUnaCasillaVaciaNoPuedeParticiparEnAtaque();
    }

    @Override
    public ArrayList<Recurso> requisitosMateriales() {
        return null;
    }

    @Override
    public boolean perteneceAImperio(Imperio imperio) {
        return false;
    }

    @Override
    public Object obtenerEstado() {
        return null;
    }

    @Override
    public void actualizarColocable(Casilla unaCasilla) {
        // Vacia a proposito
    }
}
