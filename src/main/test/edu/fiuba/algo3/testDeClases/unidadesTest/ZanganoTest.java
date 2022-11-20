package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Excepciones.ErrorZanganoNoPuedeAtacar;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Mutalisco;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ZanganoTest {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01PuedoCrearUnZangano() {
        Zangano unZangano = new Zangano();

        assertNotNull(unZangano);
    }

    @Test
    public void test02UnZanganoAtacaAUnidadTerrestreLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();

        Zangano unZangano = new Zangano();
        Zangano otroZangano = new Zangano();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unZangano, coordenadaAtacante);
        elMapa.colocarUnaUnidad(otroZangano, coordenadaAtacado);

        assertThrows(ErrorZanganoNoPuedeAtacar.class,
                () -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }

    @Test
    public void test02UnZanganoAtacaAUnidadAereaLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();

        Zangano unZangano = new Zangano();
        Mutalisco unMutalisco = new Mutalisco();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unZangano, coordenadaAtacante);
        elMapa.colocarUnaUnidad(unMutalisco, coordenadaAtacado);

        assertThrows(ErrorZanganoNoPuedeAtacar.class,
                () -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }
}
