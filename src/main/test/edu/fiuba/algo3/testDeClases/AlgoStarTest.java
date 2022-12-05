package edu.fiuba.algo3.testDeClases;

import edu.fiuba.algo3.modelo.AlgoStar.AlgoStar;
import edu.fiuba.algo3.modelo.Excepciones.ErrorJugadorNoPuedeAccederOcupableEnemigo;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
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
        Unidad unGuardian = new Guardian();
        elMapa.colocarUnaUnidad(unGuardian, coordenadaUnidad);

        // Destruyo edificios protoss
        for (int i = 0; i < 24; i++) {
            elMapa.atacar(coordenadaUnidad, coordenadaPilon);
            unGuardian.pasarTurno();
        }
        for (int i = 0; i < 40; i++) {
            elMapa.atacar(coordenadaUnidad, coordenadaAcceso);
            unGuardian.pasarTurno();
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
        Unidad unDragon = new Dragon();
        elMapa.colocarUnaUnidad(unDragon, coordenadaUnidad);

        // Destruyo edificio Zerg
        for (int i = 0; i < 25; i++) {
            elMapa.atacar(coordenadaUnidad, coordenadaEdificioZerg);
            unDragon.pasarTurno();
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
    public void test10SeIniciaUnaPartidaSeGanaYsePuedeJugarUnaRevancha() {
        /*
         * Empiezo una partida a traves de algo star, luego teletransporto un dragon
         * hacia la base Zerg y la destruyo
         */

        Mapa elMapa = Mapa.obtener();

        AlgoStar algoStar = new AlgoStar();

        algoStar.asignarJugador("lalala01", "rojo", new Protoss());
        algoStar.asignarJugador("XZangano", "azul", new Zerg());

        algoStar.empezarJuego();

        // La partida no esta terminada
        assertFalse(algoStar.partidaTerminada());

        Casilla casillaBase = elMapa.obtenerVolcanBaseLejanaPrimeraMitad();
        Coordenada coordenadaBase = casillaBase.obtenerCoordenada();
        Coordenada coordenadaAsentamientoZerg = new Coordenada(coordenadaBase.getCoordenadaX() -2, coordenadaBase.getCoordenadaY());

        /*
         * En la coordenada coordenadaAsentamientoZerg hay un criadero, que es el unico edificio de los zergs
         */

        int coordenadaXAsentamientoZerg = coordenadaAsentamientoZerg.getCoordenadaX();
        int coordenadaYAsentamientoZerg = coordenadaAsentamientoZerg.getCoordenadaY();

        Coordenada coordenadaDragon = new Coordenada(coordenadaXAsentamientoZerg, coordenadaYAsentamientoZerg - 1);

        Unidad unDragon = new Dragon();

        elMapa.colocarUnaUnidad(unDragon, coordenadaDragon);

        for (int i = 0; i < 25; i++) {
            elMapa.atacar(coordenadaDragon, coordenadaAsentamientoZerg);
            unDragon.pasarTurno();
        }

        algoStar.terminarTurno();

        // Despues del ataque al unico edificio zerg, la partida se termino
        assertTrue(algoStar.partidaTerminada());

        algoStar.revancha();

        // La partida no esta terminada
        assertFalse(algoStar.partidaTerminada());
    }
    @Test
    public void test11SeIniciaUnaPartidaSeGanaYsePuedeJugarUnaRevancha() {
        /*
         * Empiezo una partida a traves de algo star, luego teletransporto un dragon
         * hacia la base Zerg y la destruyo
         */

        Mapa elMapa = Mapa.obtener();

        AlgoStar algoStar = new AlgoStar();

        algoStar.asignarJugador("lalala01", "rojo", new Protoss());
        algoStar.asignarJugador("XZangano", "azul", new Zerg());

        algoStar.empezarJuego();

        Casilla casillaBase = elMapa.obtenerVolcanBaseLejanaPrimeraMitad();
        Coordenada coordenadaBase = casillaBase.obtenerCoordenada();
        Coordenada coordenadaAsentamientoZerg = new Coordenada(coordenadaBase.getCoordenadaX() -2, coordenadaBase.getCoordenadaY());

        /*
         * En la coordenada coordenadaAsentamientoZerg hay un criadero, que es el unico edificio de los zergs
         */

        int coordenadaXAsentamientoZerg = coordenadaAsentamientoZerg.getCoordenadaX();
        int coordenadaYAsentamientoZerg = coordenadaAsentamientoZerg.getCoordenadaY();

        Coordenada coordenadaDragon = new Coordenada(coordenadaXAsentamientoZerg, coordenadaYAsentamientoZerg - 1);

        Unidad unDragon = new Dragon();

        elMapa.colocarUnaUnidad(unDragon, coordenadaDragon);

        for (int i = 0; i < 25; i++) {
            elMapa.atacar(coordenadaDragon, coordenadaAsentamientoZerg);
            unDragon.pasarTurno();
        }

        algoStar.terminarTurno();

        algoStar.revancha();

        // Hago los mismos movimientos que antes y la partida termina

        casillaBase = elMapa.obtenerVolcanBaseLejanaPrimeraMitad();
        coordenadaBase = casillaBase.obtenerCoordenada();
        coordenadaAsentamientoZerg = new Coordenada(coordenadaBase.getCoordenadaX() -2, coordenadaBase.getCoordenadaY());

        coordenadaXAsentamientoZerg = coordenadaAsentamientoZerg.getCoordenadaX();
        coordenadaYAsentamientoZerg = coordenadaAsentamientoZerg.getCoordenadaY();

        coordenadaDragon = new Coordenada(coordenadaXAsentamientoZerg, coordenadaYAsentamientoZerg - 1);

        Dragon otroDragon = new Dragon();

        elMapa.colocarUnaUnidad(otroDragon, coordenadaDragon);

        for (int i = 0; i < 25; i++) {
            elMapa.atacar(coordenadaDragon, coordenadaAsentamientoZerg);
            otroDragon.pasarTurno();
        }

        algoStar.terminarTurno();

    }
}
