package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Scout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ScoutTest {

    @BeforeEach
    public void setup() {
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01PuedoCrearUnScout() {
        Scout unScout = new Scout();

        assertNotNull(unScout);
    }

    @Test
    public void test02UnScoutPuedeDaniarAOtroScout() {
        Mapa elMapa = Mapa.obtener();

        Scout unScout = new Scout();
        Scout otroScout = new Scout();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(1,0);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaAtacante);
        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaAtacado);

        elMapa.colocarUnaUnidad(unScout, coordenadaAtacante);
        elMapa.colocarUnaUnidad(otroScout, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }
}
