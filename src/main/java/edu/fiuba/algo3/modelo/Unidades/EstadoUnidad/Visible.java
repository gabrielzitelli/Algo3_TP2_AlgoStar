package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;

public class Visible implements Visibilidad {

    private Zealot unZealot;
    private int cantidadRequerida;
    private int contador = 0;

    public Visible(Zealot unZealot, int cantidadRequerida) {
        this.unZealot = unZealot;
        this.cantidadRequerida = cantidadRequerida;
    }

    @Override
    public void recibirAtaque(Ataque unAtaque) {
        unZealot.recibirAtaqueDefault(unAtaque);
    }

    @Override
    public Visibilidad verificarVisibilidadDe(Casilla unaCasilla) {
        return this;
    }

    @Override
    public Visibilidad aumentarContador() {
        contador++;
        if (contador == cantidadRequerida)
            return new Invisible(unZealot);

        return this;
    }

    @Override
    public boolean esInvisible() {
        return false;
    }
}
