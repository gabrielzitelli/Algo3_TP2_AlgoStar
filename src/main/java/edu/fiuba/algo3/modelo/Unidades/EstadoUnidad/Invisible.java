package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;

public class Invisible implements Visibilidad {

    private Coordenada coordenada;

    @Override
    public void recibirAtaque(Ataque unAtaque) {}

    @Override
    public void establecerCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    @Override
    public Visibilidad aumentarContador() {
        return this;
    }
}
