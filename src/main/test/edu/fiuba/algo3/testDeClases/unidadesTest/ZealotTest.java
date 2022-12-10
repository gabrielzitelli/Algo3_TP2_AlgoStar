package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.AmoSupremo;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Guardian;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;
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
        Unidad unaUnidadTerrestre = new Zerling();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarOcupable(unZealot, coordenadaAtacante);
        elMapa.colocarOcupable(unaUnidadTerrestre, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }

    @Test
    public void test03UnZealotSeMueveALaPosicionDeLaUnidadQueMata() {
        Mapa elMapa = Mapa.obtener();

        Zealot unZealot = new Zealot();
        Unidad unaUnidad = new Zerling();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarOcupable(unZealot, coordenadaAtacante);
        elMapa.colocarOcupable(unaUnidad, coordenadaAtacado);

        // El zealot mata a la otra unidad
        for (int i = 0; i < 5; i++) {
            elMapa.atacar(coordenadaAtacante, coordenadaAtacado);
            unZealot.pasarTurno();
        }

        // La posicion donde estaba la unidad atacada está ocupada
        assertThrows(ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada.class,
                () -> elMapa.colocarOcupable(new Zealot(), coordenadaAtacado));

        // La posicion donde estaba el zealot esta vacia
        assertDoesNotThrow(() -> elMapa.colocarOcupable(new Zealot(), coordenadaAtacante));
    }

    @Test
    public void test04UnZealotSeHaceInvisibleDespuesDeMatar3UnidadesYNoPuedeSerAtacado() {
        Mapa elMapa = Mapa.obtener();

        Zealot unZealot = new Zealot();
        Coordenada coordenadaZealot = new Coordenada(0,0);
        Coordenada coordenadaPrimerUnidad = new Coordenada(0,1);
        Coordenada coordenadaSegundaUnidad = new Coordenada(0,2);
        Coordenada coordenadaTercerUnidad = new Coordenada(0,3);

        elMapa.colocarOcupable(unZealot, coordenadaZealot);
        elMapa.colocarOcupable(new Zerling(), coordenadaPrimerUnidad);
        elMapa.colocarOcupable(new Zerling(), coordenadaSegundaUnidad);
        elMapa.colocarOcupable(new Zerling(), coordenadaTercerUnidad);

        // El zealot mata a la primer unidad
        for (int i = 0; i < 5; i++) {
            elMapa.atacar(coordenadaZealot, coordenadaPrimerUnidad);
            unZealot.pasarTurno();
        }
        // El zealot mata a la segunda unidad
        for (int i = 0; i < 5; i++) {
            elMapa.atacar(coordenadaPrimerUnidad, coordenadaSegundaUnidad);
            unZealot.pasarTurno();
        }
        // El zealot mata a la tercer unidad
        for (int i = 0; i < 5; i++) {
            elMapa.atacar(coordenadaSegundaUnidad, coordenadaTercerUnidad);
            unZealot.pasarTurno();
        }

        Coordenada coordenadaAtacante = new Coordenada(0,2);
        Unidad unZerling = new Zerling();
        elMapa.colocarOcupable(unZerling, coordenadaAtacante);
        // La nueva unidad intenta matar al zealot
        for (int i = 0; i < 8; i++) {
            elMapa.atacar(coordenadaAtacante, coordenadaTercerUnidad);
            unZerling.pasarTurno();
        }

        // El zealot sigue vivo
        assertThrows(ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada.class,
                () -> elMapa.colocarOcupable(new Zealot(), coordenadaTercerUnidad));
    }

    @Test
    public void test05UnZealotSeHaceInvisibleDespuesDeDestruir3EdificiosYNoPuedeSerAtacado() {
        Mapa elMapa = Mapa.obtener();

        Zealot unZealot = new Zealot();
        Coordenada coordenadaZealot = new Coordenada(0,0);
        Coordenada coordenadaPrimerEdificio = new Coordenada(0,1);
        Coordenada coordenadaSegundoEdificio = new Coordenada(0,2);
        Coordenada coordenadaTercerEdificio = new Coordenada(0,3);

        elMapa.colocarOcupable(unZealot, coordenadaZealot);
        elMapa.colocarOcupable(new Criadero(), coordenadaPrimerEdificio);
        elMapa.colocarOcupable(new Criadero(), coordenadaSegundoEdificio);
        elMapa.colocarOcupable(new Criadero(), coordenadaTercerEdificio);

        // El zealot destruye al primer edificio
        for (int i = 0; i < 63; i++) {
            elMapa.atacar(coordenadaZealot, coordenadaPrimerEdificio);
            unZealot.pasarTurno();
        }
        // El zealot destruye al segundo edificio
        for (int i = 0; i < 63; i++) {
            elMapa.atacar(coordenadaPrimerEdificio, coordenadaSegundoEdificio);
            unZealot.pasarTurno();
        }
        // El zealot destruye al tercer edificio
        for (int i = 0; i < 63; i++) {
            elMapa.atacar(coordenadaSegundoEdificio, coordenadaTercerEdificio);
            unZealot.pasarTurno();
        }

        Coordenada coordenadaAtacante = new Coordenada(0,2);
        Zerling unZerling = new Zerling();
        elMapa.colocarOcupable(unZerling, coordenadaAtacante);
        // La nueva unidad intenta matar al zealot
        for (int i = 0; i < 8; i++) {
            elMapa.atacar(coordenadaAtacante, coordenadaTercerEdificio);
            unZerling.pasarTurno();
        }

        // El zealot sigue vivo
        assertThrows(ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada.class,
                () -> elMapa.colocarOcupable(new Zealot(), coordenadaTercerEdificio));
    }

    @Test
    public void test06UnZealotInvisiblePuedeAtacarYMatarAOtraUnidadYSeguirSiendoInvisible() {
        Mapa elMapa = Mapa.obtener();

        Zealot unZealot = new Zealot();
        Coordenada coordenadaZealot = new Coordenada(0,0);
        Coordenada coordenadaPrimerUnidad = new Coordenada(0,1);
        Coordenada coordenadaSegundaUnidad = new Coordenada(0,2);
        Coordenada coordenadaTercerUnidad = new Coordenada(0,3);

        elMapa.colocarOcupable(unZealot, coordenadaZealot);
        elMapa.colocarOcupable(new Zerling(), coordenadaPrimerUnidad);
        elMapa.colocarOcupable(new Zerling(), coordenadaSegundaUnidad);
        elMapa.colocarOcupable(new Zerling(), coordenadaTercerUnidad);

        // El zealot mata a la primer unidad
        for (int i = 0; i < 5; i++) {
            elMapa.atacar(coordenadaZealot, coordenadaPrimerUnidad);
            unZealot.pasarTurno();
        }
        // El zealot mata a la segunda unidad
        for (int i = 0; i < 5; i++) {
            elMapa.atacar(coordenadaPrimerUnidad, coordenadaSegundaUnidad);
            unZealot.pasarTurno();
        }
        // El zealot mata a la tercer unidad
        for (int i = 0; i < 5; i++) {
            elMapa.atacar(coordenadaSegundaUnidad, coordenadaTercerUnidad);
            unZealot.pasarTurno();
        }

        // El zealot mata a una cuarta unidad
        Coordenada coordenadaCuartaUnidad = new Coordenada(0,4);
        Unidad unZerling = new Zerling();
        elMapa.colocarOcupable(unZerling, coordenadaCuartaUnidad);
        for (int i = 0; i < 5; i++) {
            elMapa.atacar(coordenadaTercerUnidad, coordenadaCuartaUnidad);
            unZealot.pasarTurno();
        }

        // La posicion donde estaba la unidad atacada está ocupada
        assertThrows(ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada.class,
                () -> elMapa.colocarOcupable(new Zealot(), coordenadaCuartaUnidad));

        // La posicion donde estaba el zealot esta vacia
        assertDoesNotThrow(() -> elMapa.colocarOcupable(new Zealot(), coordenadaTercerUnidad));
    }

    @Test
    public void test07UnZealotReveladoPuedeAtacarYMatarAOtraUnidadYSeguiraEstandoRevelado() {
        Mapa elMapa = Mapa.obtener();

        Zealot unZealot = new Zealot();
        Coordenada coordenadaZealot = new Coordenada(0,0);
        Coordenada coordenadaPrimerUnidad = new Coordenada(0,1);
        Coordenada coordenadaSegundaUnidad = new Coordenada(0,2);
        Coordenada coordenadaTercerUnidad = new Coordenada(0,3);

        elMapa.colocarOcupable(unZealot, coordenadaZealot);
        elMapa.colocarOcupable(new Zerling(), coordenadaPrimerUnidad);
        elMapa.colocarOcupable(new Zerling(), coordenadaSegundaUnidad);
        elMapa.colocarOcupable(new Zerling(), coordenadaTercerUnidad);

        // El zealot mata a la primer unidad
        for (int i = 0; i < 5; i++) {
            elMapa.atacar(coordenadaZealot, coordenadaPrimerUnidad);
            unZealot.pasarTurno();
        }
        // El zealot mata a la segunda unidad
        for (int i = 0; i < 5; i++) {
            elMapa.atacar(coordenadaPrimerUnidad, coordenadaSegundaUnidad);
            unZealot.pasarTurno();
        }
        // El zealot mata a la tercer unidad
        for (int i = 0; i < 5; i++) {
            elMapa.atacar(coordenadaSegundaUnidad, coordenadaTercerUnidad);
            unZealot.pasarTurno();
        }

        // Revelamos al Zealot con una unidad que revele
        elMapa.colocarOcupable(new AmoSupremo(), new Coordenada(1, 3));

        // El zealot revelado mata a una cuarta unidad
        Coordenada coordenadaCuartaUnidad = new Coordenada(0,4);
        Unidad unZerling = new Zerling();
        elMapa.colocarOcupable(unZerling, coordenadaCuartaUnidad);
        for (int i = 0; i < 5; i++) {
            elMapa.atacar(coordenadaTercerUnidad, coordenadaCuartaUnidad);
            unZealot.pasarTurno();
        }

        // Creo unidad para matar al Zealot
        Coordenada coordenadaAtacante = new Coordenada(0,3);
        Unidad unGuardian = new Guardian();
        elMapa.colocarOcupable(unGuardian, coordenadaAtacante);
        for (int i = 0; i < 7; i++) {
            elMapa.atacar(coordenadaAtacante, coordenadaCuartaUnidad);
            unGuardian.pasarTurno();
        }

        // El Zealot esta muerto
        assertDoesNotThrow(() -> elMapa.colocarOcupable(new Zealot(), coordenadaCuartaUnidad));
    }
}
