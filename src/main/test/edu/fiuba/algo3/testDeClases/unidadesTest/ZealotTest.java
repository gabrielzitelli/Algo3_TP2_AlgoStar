package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Pilon;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.AmoSupremo;
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
        Unidad unaUnidadTerrestre = new Zealot();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unZealot, coordenadaAtacante);
        elMapa.colocarUnaUnidad(unaUnidadTerrestre, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }

    @Test
    public void test03UnZealotSeMueveALaPosicionDeLaUnidadQueMata() {
        Mapa elMapa = Mapa.obtener();

        Zealot unZealot = new Zealot();
        Unidad unaUnidad = new Zealot();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unZealot, coordenadaAtacante);
        elMapa.colocarUnaUnidad(unaUnidad, coordenadaAtacado);

        // El zealot mata a la otra unidad
        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaAtacante, coordenadaAtacado);
        }

        // La posicion donde estaba la unidad atacada está ocupada
        assertThrows(ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada.class,
                () -> elMapa.colocarUnaUnidad(new Zealot(), coordenadaAtacado));

        // La posicion donde estaba el zealot esta vacia
        assertDoesNotThrow(() -> elMapa.colocarUnaUnidad(new Zealot(), coordenadaAtacante));
    }

    @Test
    public void test04UnZealotSeHaceInvisibleDespuesDeMatar3UnidadesYNoPuedeSerAtacado() {
        Mapa elMapa = Mapa.obtener();

        Zealot unZealot = new Zealot();
        Coordenada coordenadaZealot = new Coordenada(0,0);
        Coordenada coordenadaPrimerUnidad = new Coordenada(0,1);
        Coordenada coordenadaSegundaUnidad = new Coordenada(0,2);
        Coordenada coordenadaTercerUnidad = new Coordenada(0,3);

        elMapa.colocarUnaUnidad(unZealot, coordenadaZealot);
        elMapa.colocarUnaUnidad(new Zealot(), coordenadaPrimerUnidad);
        elMapa.colocarUnaUnidad(new Zealot(), coordenadaSegundaUnidad);
        elMapa.colocarUnaUnidad(new Zealot(), coordenadaTercerUnidad);

        // El zealot mata a la primer unidad
        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaZealot, coordenadaPrimerUnidad);
        }
        // El zealot mata a la segunda unidad
        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaPrimerUnidad, coordenadaSegundaUnidad);
        }
        // El zealot mata a la tercer unidad
        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaSegundaUnidad, coordenadaTercerUnidad);
        }

        Coordenada coordenadaAtacante = new Coordenada(0,2);
        elMapa.colocarUnaUnidad(new Dragon(), coordenadaAtacante);
        // La nueva unidad intenta matar al zealot
        for (int i = 0; i < 8; i++) {
            elMapa.atacar(coordenadaAtacante, coordenadaTercerUnidad);
        }

        // El zealot sigue vivo
        assertThrows(ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada.class,
                () -> elMapa.colocarUnaUnidad(new Zealot(), coordenadaTercerUnidad));
    }

    @Test
    public void test05UnZealotSeHaceInvisibleDespuesDeDestruir3EdificiosYNoPuedeSerAtacado() {
        Mapa elMapa = Mapa.obtener();

        Zealot unZealot = new Zealot();
        Coordenada coordenadaZealot = new Coordenada(0,0);
        Coordenada coordenadaPrimerEdificio = new Coordenada(0,1);
        Coordenada coordenadaSegundoEdificio = new Coordenada(0,2);
        Coordenada coordenadaTercerEdificio = new Coordenada(0,3);

        elMapa.colocarUnaUnidad(unZealot, coordenadaZealot);
        elMapa.construirEdificio(new Pilon(), coordenadaPrimerEdificio);
        elMapa.construirEdificio(new Pilon(), coordenadaSegundoEdificio);
        elMapa.construirEdificio(new Pilon(), coordenadaTercerEdificio);

        // El zealot destruye al primer edificio
        for (int i = 0; i < 75; i++) {
            elMapa.atacar(coordenadaZealot, coordenadaPrimerEdificio);
        }
        // El zealot destruye al segundo edificio
        for (int i = 0; i < 75; i++) {
            elMapa.atacar(coordenadaPrimerEdificio, coordenadaSegundoEdificio);
        }
        // El zealot destruye al tercer edificio
        for (int i = 0; i < 75; i++) {
            elMapa.atacar(coordenadaSegundoEdificio, coordenadaTercerEdificio);
        }

        Coordenada coordenadaAtacante = new Coordenada(0,2);
        elMapa.colocarUnaUnidad(new Dragon(), coordenadaAtacante);
        // La nueva unidad intenta matar al zealot
        for (int i = 0; i < 8; i++) {
            elMapa.atacar(coordenadaAtacante, coordenadaTercerEdificio);
        }

        // El zealot sigue vivo
        assertThrows(ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada.class,
                () -> elMapa.colocarUnaUnidad(new Zealot(), coordenadaTercerEdificio));
    }

    @Test
    public void test06UnZealotInvisiblePuedeAtacarYMatarAOtraUnidadYSeguirSiendoInvisible() {
        Mapa elMapa = Mapa.obtener();

        Zealot unZealot = new Zealot();
        Coordenada coordenadaZealot = new Coordenada(0,0);
        Coordenada coordenadaPrimerUnidad = new Coordenada(0,1);
        Coordenada coordenadaSegundaUnidad = new Coordenada(0,2);
        Coordenada coordenadaTercerUnidad = new Coordenada(0,3);

        elMapa.colocarUnaUnidad(unZealot, coordenadaZealot);
        elMapa.colocarUnaUnidad(new Zealot(), coordenadaPrimerUnidad);
        elMapa.colocarUnaUnidad(new Zealot(), coordenadaSegundaUnidad);
        elMapa.colocarUnaUnidad(new Zealot(), coordenadaTercerUnidad);

        // El zealot mata a la primer unidad
        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaZealot, coordenadaPrimerUnidad);
        }
        // El zealot mata a la segunda unidad
        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaPrimerUnidad, coordenadaSegundaUnidad);
        }
        // El zealot mata a la tercer unidad
        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaSegundaUnidad, coordenadaTercerUnidad);
        }

        // El zealot mata a una cuarta unidad
        Coordenada coordenadaCuartaUnidad = new Coordenada(0,4);
        elMapa.colocarUnaUnidad(new Zealot(), coordenadaCuartaUnidad);
        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaTercerUnidad, coordenadaCuartaUnidad);
        }

        // La posicion donde estaba la unidad atacada está ocupada
        assertThrows(ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada.class,
                () -> elMapa.colocarUnaUnidad(new Zealot(), coordenadaCuartaUnidad));

        // La posicion donde estaba el zealot esta vacia
        assertDoesNotThrow(() -> elMapa.colocarUnaUnidad(new Zealot(), coordenadaTercerUnidad));
    }

    @Test
    public void test07UnZealotReveladoPuedeAtacarYMatarAOtraUnidadYSeguiraEstandoRevelado() {
        Mapa elMapa = Mapa.obtener();

        Zealot unZealot = new Zealot();
        Coordenada coordenadaZealot = new Coordenada(0,0);
        Coordenada coordenadaPrimerUnidad = new Coordenada(0,1);
        Coordenada coordenadaSegundaUnidad = new Coordenada(0,2);
        Coordenada coordenadaTercerUnidad = new Coordenada(0,3);

        elMapa.colocarUnaUnidad(unZealot, coordenadaZealot);
        elMapa.colocarUnaUnidad(new Zealot(), coordenadaPrimerUnidad);
        elMapa.colocarUnaUnidad(new Zealot(), coordenadaSegundaUnidad);
        elMapa.colocarUnaUnidad(new Zealot(), coordenadaTercerUnidad);

        // El zealot mata a la primer unidad
        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaZealot, coordenadaPrimerUnidad);
        }
        // El zealot mata a la segunda unidad
        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaPrimerUnidad, coordenadaSegundaUnidad);
        }
        // El zealot mata a la tercer unidad
        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaSegundaUnidad, coordenadaTercerUnidad);
        }

        // Revelamos al Zealot con una unidad que revele
        elMapa.colocarUnaUnidad(new AmoSupremo(), new Coordenada(1, 3));

        // El zealot revelado mata a una cuarta unidad
        Coordenada coordenadaCuartaUnidad = new Coordenada(0,4);
        elMapa.colocarUnaUnidad(new Zealot(), coordenadaCuartaUnidad);
        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaTercerUnidad, coordenadaCuartaUnidad);
        }

        // Creo unidad para matar al Zealot
        Coordenada coordenadaAtacante = new Coordenada(0,3);
        elMapa.colocarUnaUnidad(new Dragon(), coordenadaAtacante);
        for (int i = 0; i < 8; i++) {
            elMapa.atacar(coordenadaAtacante, coordenadaCuartaUnidad);
        }

        // El Zealot esta muerto
        assertDoesNotThrow(() -> elMapa.colocarUnaUnidad(new Zealot(), coordenadaCuartaUnidad));
    }
}
