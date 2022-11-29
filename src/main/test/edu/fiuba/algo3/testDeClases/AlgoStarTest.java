package edu.fiuba.algo3.testDeClases;

import edu.fiuba.algo3.modelo.AlgoStar.AlgoStar;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        algoStar.asignarJugador("miguel", "rojo", new Zerg());
        algoStar.asignarJugador("marcos", "azul", new Protoss());

        algoStar.empezarJuego();

        String jugadorActual = algoStar.conseguirJugadorActual().conseguirNombre();

        assertEquals(jugadorActual, jugadorEsperado);
    }
}
