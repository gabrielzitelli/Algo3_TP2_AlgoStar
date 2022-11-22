package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void test03UnZealotSeMueveALaPosicionDeLaUnidadQueMata() {
        Mapa elMapa = Mapa.obtener();

        Zealot unZealot = new Zealot();
        Zealot otroZealot = new Zealot();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unZealot, coordenadaAtacante);
        elMapa.colocarUnaUnidad(otroZealot, coordenadaAtacado);

        // El zealot mata a la otra unidad
        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaAtacante, coordenadaAtacado);
        }

        // La posicion donde estaba la unidad atacada está ocupada
        assertThrows(ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada.class,
                () -> elMapa.colocarUnaUnidad(new Zealot(), coordenadaAtacado));

        // La posicion donde estaba el zerling esta vacia
        assertDoesNotThrow(() -> elMapa.colocarUnaUnidad(new Zealot(), coordenadaAtacante));
    }

    @Test
    public void test04UnZealotSeHaceInvisibleDespuesDeMatar3UnidadesYNoPuedeSerAtacado() {
        Mapa elMapa = Mapa.obtener();

        Zealot unZealot = new Zealot();
        Coordenada coordenadaZealot = new Coordenada(0,0);
        Coordenada coordenadaPrimerZealot = new Coordenada(0,1);
        Coordenada coordenadaSegundoZealot = new Coordenada(0,2);
        Coordenada coordenadaTercerZealot = new Coordenada(0,3);

        elMapa.colocarUnaUnidad(unZealot, coordenadaZealot);
        elMapa.colocarUnaUnidad(new Zealot(), coordenadaPrimerZealot);
        elMapa.colocarUnaUnidad(new Zealot(), coordenadaSegundoZealot);
        elMapa.colocarUnaUnidad(new Zealot(), coordenadaTercerZealot);

        // El zealot mata a la primer unidad
        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaZealot, coordenadaPrimerZealot);
        }
        // El zealot mata a la segunda unidad
        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaPrimerZealot, coordenadaSegundoZealot);
        }
        // El zealot mata a la tercer unidad
        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaSegundoZealot, coordenadaTercerZealot);
        }

        Coordenada coordenadaAtacante = new Coordenada(0,2);
        elMapa.colocarUnaUnidad(new Dragon(), coordenadaAtacante);
        // La nueva unidad intenta matar al zealot
        for (int i = 0; i < 8; i++) {
            elMapa.atacar(coordenadaAtacante, coordenadaTercerZealot);
        }

        // El zealot sigue vivo
        assertThrows(ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada.class,
                () -> elMapa.colocarUnaUnidad(new Zealot(), coordenadaTercerZealot));
    }
}
