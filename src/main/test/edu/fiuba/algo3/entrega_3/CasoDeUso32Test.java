package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.AlgoStar.AlgoStar;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Acceso;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidadesDragon;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
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

        Casilla casillaBaseZerg = elMapa.obtenerVolcanBaseLejanaPrimeraMitad();
        Casilla casillaBaseProtoss = elMapa.obtenerVolcanBaseLejanaSegundaMitad();
        Coordenada coordenadaBaseZerg = casillaBaseZerg.obtenerCoordenada();
        Coordenada coordenadaBaseProtoss = casillaBaseProtoss.obtenerCoordenada();

        Coordenada coordenadaAsentamientoZerg = new Coordenada(coordenadaBaseZerg.getCoordenadaX() -2, coordenadaBaseZerg.getCoordenadaY());
        Coordenada coordenadaAsentamientoProtoss = new Coordenada(coordenadaBaseProtoss.getCoordenadaX() -2, coordenadaBaseProtoss.getCoordenadaY());

        /*
        * En la coordenada coordenadaAsentamientoZerg hay un criadero, que es el unico edificio de los zergs
        */
        //simulamos la obtencion de materiales
        Mineral mineralesNuevos = new Mineral(1000);
        Gas gasesNuevos = new Gas(1000);
        algoStar.conseguirJugadorActual().conseguirImperio().abastecerDeRecursos(mineralesNuevos, gasesNuevos);

        Acceso accesoInicial = (Acceso) algoStar.conseguirOcupableEn(coordenadaAsentamientoProtoss);
        accesoInicial.asignarRecursos(mineralesNuevos, gasesNuevos);
        accesoInicial.crearUnidad(new FabricasUnidadesDragon());

        for (int i = 0; i < 12; i++){
            algoStar.terminarTurno();
        }

        Dragon dragon = (Dragon) algoStar.conseguirOcupableEn(new Coordenada(9,33));

        while (dragon.obtenerCoordenada().getCoordenadaX() < coordenadaAsentamientoZerg.getCoordenadaX() - 4){
            Coordenada coordenadaDestino = new Coordenada(dragon.obtenerCoordenada().getCoordenadaX() + 5, dragon.obtenerCoordenada().getCoordenadaY());
            dragon.moverA(coordenadaDestino);
            algoStar.terminarTurno();
            algoStar.terminarTurno();
        }
        while (dragon.obtenerCoordenada().getCoordenadaY() < coordenadaAsentamientoZerg.getCoordenadaY() - 3) {
            Coordenada coordenadaDestino = new Coordenada(dragon.obtenerCoordenada().getCoordenadaX(), dragon.obtenerCoordenada().getCoordenadaY() + 3);
            dragon.moverA(coordenadaDestino);
            algoStar.terminarTurno();
            algoStar.terminarTurno();
        }

        while (!algoStar.partidaTerminada()) {
            elMapa.atacar(dragon.obtenerCoordenada(), coordenadaAsentamientoZerg);
            algoStar.terminarTurno();
            algoStar.terminarTurno();
        }
        algoStar.terminarTurno();

        // Despues del ataque al unico edificio zerg, la partida se termino
        assertTrue(algoStar.partidaTerminada());
    }
}
