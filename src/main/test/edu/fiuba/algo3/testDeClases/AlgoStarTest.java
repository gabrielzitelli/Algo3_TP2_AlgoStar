package edu.fiuba.algo3.testDeClases;

import edu.fiuba.algo3.modelo.AlgoStar.AlgoStar;
import edu.fiuba.algo3.modelo.Excepciones.ErrorJugadorNoPuedeAccederOcupableEnemigo;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlgoStarTest {

    @BeforeEach
    public void setup(){
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        elMapa.recolocarBasesIniciales();
    }

    @Test
    public void test01UnaPartidaNuevaEmpiezaConElPrimerJugadorRegistrado() {
        String jugadorEsperado = "miguel";
        AlgoStar algoStar = new AlgoStar();

        algoStar.asignarJugador("miguel", "rojo", new Protoss());
        algoStar.asignarJugador("marcos", "azul", new Zerg());

        algoStar.empezarJuego();

        String jugadorActual = algoStar.conseguirJugadorActual().conseguirNombre();

        assertEquals(jugadorActual, jugadorEsperado);
    }

    @Test
    public void test02UnJugadorNoPuedeAccederAUnEdificioEnemigo() {
        AlgoStar algoStar = new AlgoStar();

        algoStar.asignarJugador("miguel", "rojo", new Protoss());
        algoStar.asignarJugador("marcos", "azul", new Zerg());

        algoStar.empezarJuego();

        // Consigo coordenadas del edificio zerg inicial
        Casilla casillaBase = Mapa.obtener().obtenerVolcanBaseLejanaPrimeraMitad();
        Coordenada coordenadaBase = casillaBase.obtenerCoordenada();
        Coordenada coordenadaAsentamientoZerg = new Coordenada(coordenadaBase.getCoordenadaX() -2, coordenadaBase.getCoordenadaY());

        assertThrows(ErrorJugadorNoPuedeAccederOcupableEnemigo.class,
                () -> algoStar.conseguirOcupableEn(coordenadaAsentamientoZerg));
    }
}
