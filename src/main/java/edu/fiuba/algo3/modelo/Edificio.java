package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.CasillaVacia;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;
import edu.fiuba.algo3.modelo.vida.Vida;

public abstract class Edificio {

    protected Vida vida;
    protected Coordenada coordenada;

    public abstract void verificarConstruccion(Casilla unaCasilla);

    public void aplicarAtaque(Ataque unAtaque) {
        try {
            this.vida.aplicarAtaque(unAtaque);
        }
        catch (Exception ErrorVidaLlegoACero){
            this.destruirEdificio();
        }
    }

    protected void destruirEdificio() {
        // Capaz estoy acoplando mucho edificio y mapa con esto
        Mapa elMapa = Mapa.obtener();
        elMapa.destruirEdificio(coordenada);
    }

    public abstract void pasarTurno();
}
