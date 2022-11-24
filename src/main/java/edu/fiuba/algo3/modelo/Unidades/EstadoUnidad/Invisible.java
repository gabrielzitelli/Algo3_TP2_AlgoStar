package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.ConRevelar;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Superficie;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;
import edu.fiuba.algo3.modelo.Vida.Vida;

public class Invisible implements Visibilidad {

    private Zealot unZealot;

    public Invisible(Zealot unZealot) {
        this.unZealot = unZealot;
    }

    @Override
    public void recibirAtaque(Ataque unAtaque) {}

    @Override
    public Visibilidad verificarVisibilidadDe(Casilla unaCasilla) {
        if (unaCasilla.tieneEsteRevelable(new ConRevelar()))
            return new Detectado(unZealot);

        return this;
    }

    @Override
    public Visibilidad aumentarContador() {
        return this;
    }
}
