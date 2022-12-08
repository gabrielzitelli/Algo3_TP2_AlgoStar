package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Ataque.TipoDanio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorLaUnidadNoPuedeAtacarFueraDeSuRango;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;

public class Atacante implements Atacar{

    private final int rangoDeAtaque;

    public Atacante(int rangoDeAtaque) {
        this.rangoDeAtaque = rangoDeAtaque;
    }

    @Override
    public Atacar atacar(Coordenada coordenada, Casilla casillaAAtacar, TipoDanio unDanio) {
        if (!Mapa.obtener().estaDentroDeRango(coordenada, casillaAAtacar, rangoDeAtaque))
            throw new ErrorLaUnidadNoPuedeAtacarFueraDeSuRango();

        casillaAAtacar.recibirAtaque(new Ataque(unDanio));

        return new NoAtacante(rangoDeAtaque);
    }

    @Override
    public Atacar pasarTurno() {
        return this;
    }

    public String toString() {
        return "atacar";
    }
}
