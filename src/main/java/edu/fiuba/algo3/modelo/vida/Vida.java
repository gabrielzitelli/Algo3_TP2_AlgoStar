package edu.fiuba.algo3.modelo.vida;


import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;

public interface Vida {

    public abstract void aplicarAtaque(Ataque unAtaque);
    public abstract void pasarTurno();
}
