package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.ConRevelar;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;

public class Invisible implements Visibilidad {

    private Zealot unidadInvisible;

    public Invisible(Zealot unidadInvisible) {
        this.unidadInvisible = unidadInvisible;
    }

    @Override
    public void recibirAtaque(Ataque unAtaque) {}

    @Override
    public Visibilidad verificarVisibilidadDe(Casilla unaCasilla) {
        if (unaCasilla.tieneEsteRevelable(new ConRevelar()))
            return new Detectado(unidadInvisible);

        return this;
    }

    @Override
    public Visibilidad aumentarContador() {
        return this;
    }

    @Override
    public boolean esInvisible() {
        return true;
    }
}
