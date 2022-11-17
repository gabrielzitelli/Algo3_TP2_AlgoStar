package edu.fiuba.algo3.modelo.Unidades;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Superficie;
import edu.fiuba.algo3.modelo.Ataque.Ataque;

import java.util.ArrayList;

public interface Unidad {

    public abstract Ataque atacar();
    public abstract void recibirAtaque(Ataque unAtaque);
    public abstract ArrayList<Recurso> requisitosMateriales();
    public abstract boolean esIgualA(Unidad unidad);
    public abstract boolean estaConstruida();
    public abstract void pasarTurno();
    public abstract void verificarSuperficie(Superficie superficie);
}
