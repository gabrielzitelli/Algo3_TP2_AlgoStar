package edu.fiuba.algo3.modelo.Vida;


import edu.fiuba.algo3.modelo.Ataque.Ataque;

public interface Vida {

    public abstract void aplicarAtaque(Ataque unAtaque);
    public abstract void pasarTurno();
}
