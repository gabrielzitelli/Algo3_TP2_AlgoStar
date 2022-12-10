package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ZerlingTest {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01PuedoCrearUnZerling(){
        Zerling unZerling = new Zerling();

        assertNotNull(unZerling);
    }

    @Test
    public void test02UnZerlingPuedeDaniarAUnaUnidadTerrestre(){
        Mapa elMapa = Mapa.obtener();

        Zerling unZerling = new Zerling();
        Zealot unZealot = new Zealot();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarOcupable(unZerling, coordenadaAtacante);
        elMapa.colocarOcupable(unZealot, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }

    /*@Test
    public void test03UnZerlingPuedeDaniarAUnPilon(){
        Zerling unZerling = new Zerling();
        Pilon pilon = new Pilon();

        Coordenada unaCoordenada = new Coordenada(0,0);
        Coordenada otraCoordenada = new Coordenada(0,1);

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarUnaUnidad(unZerling, unaCoordenada);
        elMapa.construirEdificio(pilon, otraCoordenada);

        assertDoesNotThrow(() -> elMapa.atacar(unaCoordenada, otraCoordenada));
    }*/
}
