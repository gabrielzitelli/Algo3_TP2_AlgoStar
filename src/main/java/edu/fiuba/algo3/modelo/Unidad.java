package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;

public interface Unidad {
    public Ataque atacar();

    public void recibirAtaque(Ataque unAtaque);
}
