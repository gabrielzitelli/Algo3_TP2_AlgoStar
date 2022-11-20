package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Mutalisco;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MutaliscoTest {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01PuedoCrearUnMutalisco() {
        Mutalisco unMutalisco = new Mutalisco();

        assertNotNull(unMutalisco);
    }

    @Test
    public void test02UnMutaliscoPuedeDaniarAUnaUnidadTerrestre() {
        Mapa elMapa = Mapa.obtener();

        Mutalisco unMutalisco = new Mutalisco();
        Zerling unZerling = new Zerling();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unMutalisco, coordenadaAtacante);
        elMapa.colocarUnaUnidad(unZerling, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }

    @Test
    public void test03UnMutaliscoPuedeDaniarAUnaUnidadAerea() {
        Mapa elMapa = Mapa.obtener();

        Mutalisco unMutalisco = new Mutalisco();
        Mutalisco otroMutalisco = new Mutalisco();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unMutalisco, coordenadaAtacante);
        elMapa.colocarUnaUnidad(otroMutalisco, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }
}
