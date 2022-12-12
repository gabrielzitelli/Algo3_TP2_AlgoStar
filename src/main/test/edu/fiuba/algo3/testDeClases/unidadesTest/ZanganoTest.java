package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Excepciones.ErrorLaUnidadNoPuedeAtacarFueraDeSuRango;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Scout;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        Unidad unaUnidadTerrestre = new Zealot();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarOcupable(unZangano, coordenadaAtacante);
        elMapa.colocarOcupable(unaUnidadTerrestre, coordenadaAtacado);

        assertThrows(ErrorLaUnidadNoPuedeAtacarFueraDeSuRango.class,
                () -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }

    @Test
    public void test03UnZanganoAtacaAUnidadAereaLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();

        Zangano unZangano = new Zangano();
        Unidad unaUnidadAerea = new Scout();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarOcupable(unZangano, coordenadaAtacante);
        elMapa.colocarOcupable(unaUnidadAerea, coordenadaAtacado);

        assertThrows(ErrorLaUnidadNoPuedeAtacarFueraDeSuRango.class,
                () -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }
}
