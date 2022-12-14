package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.AlgoStar.Logger;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeCaminarHastaEsaDistancia;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Ocupable;

public class Caminadora implements Caminar{

    private final int rangoDeCaminata;

    public Caminadora(int rangoDeCaminata) {
        this.rangoDeCaminata = rangoDeCaminata;
    }

    @Override
    public Caminar caminar(Coordenada coordenadaInicial, Coordenada coordenadaDestino){
        Mapa elMapa = Mapa.obtener();

        if (elMapa.estaDentroDeRango(coordenadaInicial, elMapa.obtenerCasilla(coordenadaDestino), rangoDeCaminata))
            throw new ErrorNoSePuedeCaminarHastaEsaDistancia();

        elMapa.moverUnidad(coordenadaInicial, coordenadaDestino);
        Ocupable unidadMovida = elMapa.obtenerOcupable(coordenadaDestino);
        Logger.obtener().log("Se ha movido un " + unidadMovida.getClass().getSimpleName() +
                " desde la casilla [X: " + coordenadaInicial.getCoordenadaX() + ", Y: " + coordenadaInicial.getCoordenadaY() +
                "] hasta la casilla [X: " + coordenadaDestino.getCoordenadaX() + ", Y: " + coordenadaDestino.getCoordenadaY() + "].");
        return new NoCaminadora(rangoDeCaminata);
    }

    @Override
    public Caminar pasarTurno() {
        return this;
    }

    @Override
    public String toString() {
        return "caminar";
    }
}
