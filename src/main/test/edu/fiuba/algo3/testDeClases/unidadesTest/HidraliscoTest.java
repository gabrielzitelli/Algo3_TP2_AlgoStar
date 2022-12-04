package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Scout;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Hidralisco;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Mutalisco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HidraliscoTest {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01PuedoCrearUnHidralisco() {
        Hidralisco unHidralisco = new Hidralisco();

        assertNotNull(unHidralisco);
    }

    @Test
    public void test02UnHidraliscoPuedeDaniarAUnaUnidadTerrestre() {
        Mapa elMapa = Mapa.obtener();

        Hidralisco unHidralisco = new Hidralisco();
        Dragon unDragon = new Dragon();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unHidralisco, coordenadaAtacante);
        elMapa.colocarUnaUnidad(unDragon, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }

    @Test
    public void test03UnHidraliscoPuedeDaniarAUnaUnidadAerea() {
        Mapa elMapa = Mapa.obtener();

        Hidralisco unHidralisco = new Hidralisco();
        Scout unScout = new Scout();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unHidralisco, coordenadaAtacante);
        elMapa.colocarUnaUnidad(unScout, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }
}
