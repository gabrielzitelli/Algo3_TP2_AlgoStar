package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Excepciones.ErrorFuegoCompañero;
import edu.fiuba.algo3.modelo.Excepciones.ErrorUnidadYaAtacoEsteTurno;
import edu.fiuba.algo3.modelo.Excepciones.ErrorUnidadYaSeMovioEsteTurno;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Hidralisco;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnidadTest {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01UnaUnidadZergNoPuedeAtacarAOtraUnidadZerg() {
        Mapa elMapa = Mapa.obtener();

        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacada = new Coordenada(0,1);

        elMapa.colocarOcupable(new Zerling(), coordenadaAtacante);
        elMapa.colocarOcupable(new Hidralisco(), coordenadaAtacada);

        assertThrows(ErrorFuegoCompañero.class,
                () -> elMapa.atacar(coordenadaAtacante, coordenadaAtacada));
    }

    @Test
    public void test02UnaUnidadProtossNoPuedeAtacarAOtraUnidadProtoss() {
        Mapa elMapa = Mapa.obtener();

        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacada = new Coordenada(0,1);

        elMapa.colocarOcupable(new Zealot(), coordenadaAtacante);
        elMapa.colocarOcupable(new Dragon(), coordenadaAtacada);

        assertThrows(ErrorFuegoCompañero.class,
                () -> elMapa.atacar(coordenadaAtacante, coordenadaAtacada));
    }

    @Test
    public void test03UnaUnidadNoPuedeAtacarMasDeUnaVezPorTurno() {
        Mapa elMapa = Mapa.obtener();

        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacada = new Coordenada(0,1);

        elMapa.colocarOcupable(new Zerling(), coordenadaAtacante);
        elMapa.colocarOcupable(new Dragon(), coordenadaAtacada);

        elMapa.atacar(coordenadaAtacante, coordenadaAtacada);

        assertThrows(ErrorUnidadYaAtacoEsteTurno.class,
                () -> elMapa.atacar(coordenadaAtacante, coordenadaAtacada));
    }

    @Test
    public void test04UnaUnidadPuedeAtacarNuevamenteDespuesDePasarElTurno() {
        Mapa elMapa = Mapa.obtener();

        Unidad unidadAtacadora = new Zerling();

        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacada = new Coordenada(0,1);

        elMapa.colocarOcupable(unidadAtacadora, coordenadaAtacante);
        elMapa.colocarOcupable(new Dragon(), coordenadaAtacada);

        elMapa.atacar(coordenadaAtacante, coordenadaAtacada);
        unidadAtacadora.pasarTurno();

        assertDoesNotThrow(() -> elMapa.atacar(coordenadaAtacante, coordenadaAtacada));
    }

    @Test
    public void test05UnaUnidadNoPuedeMoverseMasDeUnaVezPorTurno() {
        Mapa elMapa = Mapa.obtener();

        Unidad unaUnidad = new Zerling();
        Coordenada coordenadaUnidad = new Coordenada(0,0);

        elMapa.colocarOcupable(unaUnidad, coordenadaUnidad);

        unaUnidad.moverA(new Coordenada(0, 1));

        assertThrows(ErrorUnidadYaSeMovioEsteTurno.class,
                () -> unaUnidad.moverA(new Coordenada(0, 0)));
    }

    @Test
    public void test06UnaUnidadPuedeMoverseNuevamenteDespuesDePasarElTurno() {
        Mapa elMapa = Mapa.obtener();

        Unidad unaUnidad = new Zerling();
        Coordenada coordenadaUnidad = new Coordenada(0,0);

        elMapa.colocarOcupable(unaUnidad, coordenadaUnidad);

        unaUnidad.moverA(new Coordenada(0, 1));
        unaUnidad.pasarTurno();

        assertDoesNotThrow(() -> unaUnidad.moverA(new Coordenada(0, 2)));
    }
}
