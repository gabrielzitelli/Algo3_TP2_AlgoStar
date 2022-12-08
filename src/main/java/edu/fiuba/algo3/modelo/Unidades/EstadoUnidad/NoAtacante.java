package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.Ataque.TipoDanio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorUnidadYaAtacoEsteTurno;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;

public class NoAtacante implements Atacar{

    private final int rangoDeAtaque;

    public NoAtacante(int rangoDeAtaque) {
        this.rangoDeAtaque = rangoDeAtaque;
    }

    @Override
    public Atacar atacar(Coordenada coordenada, Casilla casillaAAtacar, TipoDanio unDanio){
        throw new ErrorUnidadYaAtacoEsteTurno();
    }

    @Override
    public Atacar pasarTurno() {
        return new Atacante(rangoDeAtaque);
    }
}
