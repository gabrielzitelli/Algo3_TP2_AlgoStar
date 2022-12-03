package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeCaminarHastaEsaDistancia;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;

public class Caminadora implements Caminar{

    @Override
    public void caminar(Coordenada coordenadaInicial, Coordenada coordenadaDestino){
        Mapa elMapa = Mapa.obtener();
        if ( estaDentroDelRadio(coordenadaInicial,coordenadaDestino) ){
            elMapa.moverUnidad(coordenadaInicial, coordenadaDestino);
        }
        else{
            throw new ErrorNoSePuedeCaminarHastaEsaDistancia();
        }

    }

    private boolean estaDentroDelRadio(Coordenada coordenadaInicial, Coordenada coordenadaFinal){
        int xCoordenadaFinal, yCoordenadaFinal;
        int xCoordenadaActual, yCoordenadaActual;
        xCoordenadaFinal = coordenadaFinal.getCoordenadaX();
        yCoordenadaFinal = coordenadaFinal.getCoordenadaY();
        xCoordenadaActual = coordenadaInicial.getCoordenadaX();
        yCoordenadaActual = coordenadaInicial.getCoordenadaY();

        double circunf;
        circunf = (Math.pow((xCoordenadaFinal-xCoordenadaActual),2) + Math.pow((yCoordenadaFinal-yCoordenadaActual),2) );

        int rangoDeCaminar = 5;

        double radio = Math.pow(rangoDeCaminar,2);
        return (circunf <= radio);


    }
}
