package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoPuedeAtacarUnidadTerrestre;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Scout;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Devorador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DevoradorTest {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01PuedoCrearUnDevorador() {
        Devorador unDevorador = new Devorador();

        assertNotNull(unDevorador);
    }

    @Test
    public void test02UnDevoradorNoPuedeDaniarAUnaUnidadTerrestre() {
        Mapa elMapa = Mapa.obtener();

        Devorador unDevorador = new Devorador();
        Zealot unZealot = new Zealot();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarOcupable(unDevorador, coordenadaAtacante);
        elMapa.colocarOcupable(unZealot, coordenadaAtacado);

        assertThrows(ErrorNoPuedeAtacarUnidadTerrestre.class,
                () -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }

    @Test
    public void test03UnDevoradorPuedeDaniarAUnaUnidadAerea() {
        Mapa elMapa = Mapa.obtener();

        Devorador unDevorador = new Devorador();
        Scout unScout = new Scout();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarOcupable(unDevorador, coordenadaAtacante);
        elMapa.colocarOcupable(unScout, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }
}
