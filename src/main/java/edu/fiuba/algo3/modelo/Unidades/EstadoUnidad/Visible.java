package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Excepciones.ErrorVidaLlegoACero;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Superficie;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Vida.Vida;

public class Visible implements Visibilidad {

    private Vida vida;
    private Superficie superficieDondeSeMueve;
    private Coordenada coordenada;
    private int cantidadRequerida;
    private int contador = 0;

    public Visible(Vida vida, Superficie superficieDondeSeMueve, int cantidadRequerida) {
        this.vida = vida;
        this.superficieDondeSeMueve = superficieDondeSeMueve;
        this.cantidadRequerida = cantidadRequerida;
    }

    @Override
    public void recibirAtaque(Ataque unAtaque) {
        try {
            vida.aplicarAtaque(superficieDondeSeMueve.conseguirTipoDeAtaque(unAtaque));
        }
        catch (ErrorVidaLlegoACero e) {
            destruirUnidad();
        }
    }

    @Override
    public void establecerCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    @Override
    public Visibilidad aumentarContador() {
        contador++;
        if (contador == cantidadRequerida)
            return new Invisible();

        return this;
    }

    protected void destruirUnidad() {
        Mapa elMapa = Mapa.obtener();
        elMapa.quitarUnidad(coordenada);
    }
}
