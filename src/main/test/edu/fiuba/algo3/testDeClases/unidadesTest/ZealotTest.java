package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ZealotTest {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01PuedoCrearUnZealot() {
        Zealot unZealot = new Zealot();

        assertNotNull(unZealot);
    }

    @Test
    public void test02UnZealotPuedeDaniarAUnaUnidadTerrestre() {
        Mapa elMapa = Mapa.obtener();

        Zealot unZealot = new Zealot();
        Zealot otroZealot = new Zealot();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unZealot, coordenadaAtacante);
        elMapa.colocarUnaUnidad(otroZealot, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }
}
