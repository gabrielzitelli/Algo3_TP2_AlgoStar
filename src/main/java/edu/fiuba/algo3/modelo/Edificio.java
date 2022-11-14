package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;
import edu.fiuba.algo3.modelo.vida.Vida;

public abstract class Edificio {

    protected Vida vida;

    public abstract void verificarConstruccion(Casilla unaCasilla);

    public void aplicarAtaque(Ataque unAtaque){
        this.vida.aplicarAtaque(unAtaque);
    }

    public abstract void pasarTurno();
}
