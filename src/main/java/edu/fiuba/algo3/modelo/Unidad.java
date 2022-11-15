package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;

import java.util.ArrayList;

public interface Unidad {
    public Ataque atacar();

    public void recibirAtaque(Ataque unAtaque);

    ArrayList<Recurso> requisitosMateriales();
}
