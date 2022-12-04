package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Scout;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Guardian;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;
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
    public void test02UnScoutPuedeDaniarAUnaUnidadTerrestre() {
        Mapa elMapa = Mapa.obtener();

        Scout unScout = new Scout();
        Zerling unZerling = new Zerling();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(1,0);

        elMapa.colocarUnaUnidad(unScout, coordenadaAtacante);
        elMapa.colocarUnaUnidad(unZerling, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }

    @Test
    public void test03UnScoutPuedeDaniarAUnaUnidadAerea() {
        Mapa elMapa = Mapa.obtener();

        Scout unScout = new Scout();
        Guardian unGuardian = new Guardian();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(1,0);

        elMapa.colocarUnaUnidad(unScout, coordenadaAtacante);
        elMapa.colocarUnaUnidad(unGuardian, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }
}
