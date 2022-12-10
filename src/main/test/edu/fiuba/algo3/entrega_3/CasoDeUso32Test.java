package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.AlgoStar.AlgoStar;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasoDeUso32Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01PuedoEmpezarUnJuegoYterminarlo(){
        /*
        * Empiezo una partida a traves de algo star, luego teletransporto un dragon
        * hacia la base Zerg y la destruyo
        */

        Mapa elMapa = Mapa.obtener();
        elMapa.recolocarBasesIniciales();

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

        elMapa.colocarOcupable(unDragon, coordenadaDragon);

        for (int i = 0; i < 25; i++) {
            elMapa.atacar(coordenadaDragon, coordenadaAsentamientoZerg);
            unDragon.pasarTurno();
        }

        algoStar.terminarTurno();

        // Despues del ataque al unico edificio zerg, la partida se termino
        assertTrue(algoStar.partidaTerminada());
    }
}
