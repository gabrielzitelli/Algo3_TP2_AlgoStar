package edu.fiuba.algo3.modelo.Edificios.Vida;


import edu.fiuba.algo3.modelo.Ataque.Ataque;

public interface Vida {

    void aplicarAtaque(Ataque unAtaque);
    void pasarTurno();
}
