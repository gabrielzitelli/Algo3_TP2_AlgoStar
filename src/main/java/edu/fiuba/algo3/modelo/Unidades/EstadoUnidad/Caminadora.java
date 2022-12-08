package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeCaminarHastaEsaDistancia;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;

public class Caminadora implements Caminar{

    private final int rangoDeCaminata;

    public Caminadora(int rangoDeCaminata) {
        this.rangoDeCaminata = rangoDeCaminata;
    }

    @Override
    public Caminar caminar(Coordenada coordenadaInicial, Coordenada coordenadaDestino){
        Mapa elMapa = Mapa.obtener();

        if (!elMapa.estaDentroDeRango(coordenadaInicial, elMapa.obtenerCasilla(coordenadaDestino), rangoDeCaminata))
            throw new ErrorNoSePuedeCaminarHastaEsaDistancia();

        elMapa.moverUnidad(coordenadaInicial, coordenadaDestino);
        return new NoCaminadora(rangoDeCaminata);
    }

    @Override
    public Caminar pasarTurno() {
        return this;
    }
}
