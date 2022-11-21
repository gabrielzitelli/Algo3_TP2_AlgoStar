package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CasoDeUso24Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().recolocarBasesIniciales();
    }

    @Test
    public void test01ElMapaTieneUnaBaseDelLadoIzquierdoConMinerales(){
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        int tamanioMapa = 100;
        int mitadLadoMapa = tamanioMapa/2;
        int cuartoLadoMapa = tamanioMapa/4;

        Coordenada[] coordMineralesBase = {
                new Coordenada(mitadLadoMapa +1, cuartoLadoMapa-1),
                new Coordenada(mitadLadoMapa +1, cuartoLadoMapa),
                new Coordenada(mitadLadoMapa +1, cuartoLadoMapa+1),
        };

        protoss.abastecerDeRecursos(new Mineral(500), new Gas(0));
        protoss.construirNexoMineral(coordMineralesBase[0]);
        protoss.construirNexoMineral(coordMineralesBase[1]);

        //No se lanza excepcion porque los 3 nexos minerales se construyeron sobre un mineral
        assertDoesNotThrow(()->protoss.construirNexoMineral(coordMineralesBase[2]));
    }

    @Test
    public void test02ElMapaTieneUnaBaseDelLadoIzquierdoConGas(){
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        int tamanioMapa = 100;
        int mitadLadoMapa = tamanioMapa/2;
        int cuartoLadoMapa = tamanioMapa/4;
        Coordenada coordenadaGas = new Coordenada(mitadLadoMapa, cuartoLadoMapa);

        protoss.abastecerDeRecursos(new Mineral(500), new Gas(0));

        //No se lanza excepcion porque los 3 nexos minerales se construyeron sobre un mineral
        assertDoesNotThrow(()->protoss.construirAsimilador(coordenadaGas));
    }

}
