package edu.fiuba.algo3.testDeClases;

import edu.fiuba.algo3.modelo.AlgoStar.AlgoStar;
import edu.fiuba.algo3.modelo.Excepciones.ErrorJugadorNoPuedeAccederOcupableEnemigo;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Guardian;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    public void test02UnaPartidaNuevaNoEstaTerminada() {
        AlgoStar algoStar = new AlgoStar();

        algoStar.asignarJugador("miguel", "rojo", new Zerg());
        algoStar.asignarJugador("marcos", "azul", new Protoss());

        algoStar.empezarJuego();

        assertFalse(algoStar.partidaTerminada());
    }

    @Test
    public void test03DespuesDelTurnoDelPrimerJugadorLeTocaAlSegundoJugadorYLuegoAlPrimerJugador() {
        String primerJugador = "Miguel";
        String segundoJugador = "Marcos";

        AlgoStar algoStar = new AlgoStar();

        algoStar.asignarJugador(primerJugador, "rojo", new Protoss());
        algoStar.asignarJugador(segundoJugador, "azul", new Zerg());

        algoStar.empezarJuego();
        assertEquals(algoStar.conseguirJugadorActual().conseguirNombre(), primerJugador);

        algoStar.terminarTurno();
        assertEquals(algoStar.conseguirJugadorActual().conseguirNombre(), segundoJugador);

        algoStar.terminarTurno();
        assertEquals(algoStar.conseguirJugadorActual().conseguirNombre(), primerJugador);
    }

    @Test
    public void test04UnJugadorProtossNoPuedeAccederAUnEdificioZerg() {
        AlgoStar algoStar = new AlgoStar();

        algoStar.asignarJugador("miguel", "rojo", new Protoss());
        algoStar.asignarJugador("marcos", "azul", new Zerg());

        algoStar.empezarJuego();

        // Consigo coordenadas del edificio zerg inicial
        Casilla casillaBase = Mapa.obtener().obtenerVolcanBaseLejanaPrimeraMitad();
        Coordenada coordenadaBase = casillaBase.obtenerCoordenada();
        Coordenada coordenadaEdificioZerg = new Coordenada(coordenadaBase.getCoordenadaX() -2, coordenadaBase.getCoordenadaY());

        assertThrows(ErrorJugadorNoPuedeAccederOcupableEnemigo.class,
                () -> algoStar.conseguirOcupableEn(coordenadaEdificioZerg));
    }

    @Test
    public void test05UnJugadorZergNoPuedeAccederAUnEdificioProtoss() {
        AlgoStar algoStar = new AlgoStar();

        algoStar.asignarJugador("marcos", "azul", new Zerg());
        algoStar.asignarJugador("miguel", "rojo", new Protoss());

        algoStar.empezarJuego();

        // Consigo coordenadas del edificio protoss inicial
        Casilla casillaBase = Mapa.obtener().obtenerVolcanBaseLejanaSegundaMitad();
        Coordenada coordenadaBase = casillaBase.obtenerCoordenada();
        Coordenada coordenadaEdificioProtoss = new Coordenada(coordenadaBase.getCoordenadaX() -2, coordenadaBase.getCoordenadaY());

        assertThrows(ErrorJugadorNoPuedeAccederOcupableEnemigo.class,
                () -> algoStar.conseguirOcupableEn(coordenadaEdificioProtoss));
    }

    @Test
    public void test06UnJugadorProtossPuedeAccederAUnEdificioProtoss() {
        AlgoStar algoStar = new AlgoStar();

        algoStar.asignarJugador("miguel", "rojo", new Protoss());
        algoStar.asignarJugador("marcos", "azul", new Zerg());

        algoStar.empezarJuego();

        // Consigo coordenadas del edificio protoss inicial
        Casilla casillaBase = Mapa.obtener().obtenerVolcanBaseLejanaSegundaMitad();
        Coordenada coordenadaBase = casillaBase.obtenerCoordenada();
        Coordenada coordenadaEdificioProtoss = new Coordenada(coordenadaBase.getCoordenadaX() -2, coordenadaBase.getCoordenadaY());

        assertNotNull(algoStar.conseguirOcupableEn(coordenadaEdificioProtoss));
    }

    @Test
    public void test07UnJugadorZergPuedeAccederAUnEdificioZerg() {
        AlgoStar algoStar = new AlgoStar();

        algoStar.asignarJugador("marcos", "azul", new Zerg());
        algoStar.asignarJugador("miguel", "rojo", new Protoss());

        algoStar.empezarJuego();

        // Consigo coordenadas del edificio zerg inicial
        Casilla casillaBase = Mapa.obtener().obtenerVolcanBaseLejanaPrimeraMitad();
        Coordenada coordenadaBase = casillaBase.obtenerCoordenada();
        Coordenada coordenadaEdificioZerg = new Coordenada(coordenadaBase.getCoordenadaX() -2, coordenadaBase.getCoordenadaY());

        assertNotNull(algoStar.conseguirOcupableEn(coordenadaEdificioZerg));
    }

    @Test
    public void test08UnJugadorZergGanaAUnJugadorProtoss() {
        String primerJugador = "Miguel";
        String segundoJugador = "Marcos";

        AlgoStar algoStar = new AlgoStar();

        algoStar.asignarJugador(primerJugador, "rojo", new Zerg());
        algoStar.asignarJugador(segundoJugador, "azul", new Protoss());

        algoStar.empezarJuego();

        // Consigo coordenadas del edificio protoss inicial
        Mapa elMapa = Mapa.obtener();

        Casilla casillaBase = elMapa.obtenerVolcanBaseLejanaSegundaMitad();
        Coordenada coordenadaBase = casillaBase.obtenerCoordenada();
        Coordenada coordenadaAcceso = new Coordenada(coordenadaBase.getCoordenadaX() -2, coordenadaBase.getCoordenadaY());
        Coordenada coordenadaPilon = new Coordenada(coordenadaBase.getCoordenadaX() -3, coordenadaBase.getCoordenadaY()-1);

        // Coloco una unidad zergs al lado de los edificios protoss
        Coordenada coordenadaUnidad = new Coordenada(coordenadaBase.getCoordenadaX() - 2, coordenadaBase.getCoordenadaY() - 1);
        elMapa.colocarUnaUnidad(new Guardian(), coordenadaUnidad);

        // Destruyo edificios protoss
        for (int i = 0; i < 24; i++) {
            elMapa.atacar(coordenadaUnidad, coordenadaPilon);
        }
        for (int i = 0; i < 40; i++) {
            elMapa.atacar(coordenadaUnidad, coordenadaAcceso);
        }

        algoStar.terminarTurno();

        assertTrue(algoStar.partidaTerminada());
        assertEquals(algoStar.conseguirJugadorActual().conseguirNombre(), primerJugador);

        // Terminar turno no cambia el resultado de la partida
        algoStar.terminarTurno();
        assertTrue(algoStar.partidaTerminada());
        assertEquals(algoStar.conseguirJugadorActual().conseguirNombre(), primerJugador);
    }

    @Test
    public void test09UnJugadorProtossGanaAUnJugadorZerg() {
        String primerJugador = "Miguel";
        String segundoJugador = "Marcos";

        AlgoStar algoStar = new AlgoStar();

        algoStar.asignarJugador(primerJugador, "rojo", new Protoss());
        algoStar.asignarJugador(segundoJugador, "azul", new Zerg());

        algoStar.empezarJuego();

        // Consigo coordenadas del edificio Zerg inicial
        Mapa elMapa = Mapa.obtener();

        Casilla casillaBase = elMapa.obtenerVolcanBaseLejanaPrimeraMitad();
        Coordenada coordenadaBase = casillaBase.obtenerCoordenada();
        Coordenada coordenadaEdificioZerg = new Coordenada(coordenadaBase.getCoordenadaX() -2, coordenadaBase.getCoordenadaY());

        // Coloco una unidad Protoss al lado del edificio Zerg
        Coordenada coordenadaUnidad = new Coordenada(coordenadaBase.getCoordenadaX() - 2, coordenadaBase.getCoordenadaY() - 1);
        elMapa.colocarUnaUnidad(new Dragon(), coordenadaUnidad);

        // Destruyo edificio Zerg
        for (int i = 0; i < 25; i++) {
            elMapa.atacar(coordenadaUnidad, coordenadaEdificioZerg);
        }

        algoStar.terminarTurno();

        assertTrue(algoStar.partidaTerminada());
        assertEquals(algoStar.conseguirJugadorActual().conseguirNombre(), primerJugador);

        // Terminar turno no cambia el resultado de la partida
        algoStar.terminarTurno();
        assertTrue(algoStar.partidaTerminada());
        assertEquals(algoStar.conseguirJugadorActual().conseguirNombre(), primerJugador);
    }
}
