package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestZerling {

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
    public void test02UnZerlingPuedeDaniarAOtroZerling(){
        Mapa elMapa = Mapa.obtener();

        Zerling unZerling = new Zerling();
        Zerling ZerlingADaniar = new Zerling();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unZerling, coordenadaAtacante);
        elMapa.colocarUnaUnidad(ZerlingADaniar, coordenadaAtacado);

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
