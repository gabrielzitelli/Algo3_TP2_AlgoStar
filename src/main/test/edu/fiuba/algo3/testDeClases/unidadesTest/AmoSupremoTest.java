package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada;
import edu.fiuba.algo3.modelo.Excepciones.ErrorUnidadNoPuedeAtacar;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.AmoSupremo;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Guardian;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AmoSupremoTest {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    private Unidad crearUnidadInvisible() {
        Mapa elMapa = Mapa.obtener();

        Unidad unaUnidad = new Zealot();
        Coordenada coordenadaUnidad = new Coordenada(5,0);
        Coordenada coordenadaPrimerUnidad = new Coordenada(6,0);
        Coordenada coordenadaSegundaUnidad = new Coordenada(7,0);
        Coordenada coordenadaTercerUnidad = new Coordenada(8,0);

        elMapa.colocarUnaUnidad(unaUnidad, coordenadaUnidad);
        elMapa.colocarUnaUnidad(new Zealot(), coordenadaPrimerUnidad);
        elMapa.colocarUnaUnidad(new Zealot(), coordenadaSegundaUnidad);
        elMapa.colocarUnaUnidad(new Zealot(), coordenadaTercerUnidad);

        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaUnidad, coordenadaPrimerUnidad);
        }
        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaPrimerUnidad, coordenadaSegundaUnidad);
        }
        for (int i = 0; i < 20; i++) {
            elMapa.atacar(coordenadaSegundaUnidad, coordenadaTercerUnidad);
        }

        return unaUnidad;
    }

    @Test
    public void test01PuedoCrearUnAmoSupremo() {
        AmoSupremo unAmoSupremo = new AmoSupremo();

        assertNotNull(unAmoSupremo);
    }

    @Test
    public void test02UnAmoSupremoAtacaAUnidadTerrestreLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();

        AmoSupremo unAmoSupremo = new AmoSupremo();
        Unidad unaUnidadTerrestre = new Zangano();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unAmoSupremo, coordenadaAtacante);
        elMapa.colocarUnaUnidad(unaUnidadTerrestre, coordenadaAtacado);

        assertThrows(ErrorUnidadNoPuedeAtacar.class,
                () -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }

    @Test
    public void test03UnAmoSupremoAtacaAUnidadAereaLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();

        AmoSupremo unAmoSupremo = new AmoSupremo();
        Unidad unaUnidadAerea = new AmoSupremo();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unAmoSupremo, coordenadaAtacante);
        elMapa.colocarUnaUnidad(unaUnidadAerea, coordenadaAtacado);

        assertThrows(ErrorUnidadNoPuedeAtacar.class,
                () -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }
/*
    @Test
    public void test04UnAmoSupremoEnRangoDeUnaUnidadInvisibleLaRevelaYPuedeSerAtacada() {
        Mapa elMapa = Mapa.obtener();

        Unidad unaUnidadInvisible = crearUnidadInvisible();
        Coordenada coordenadaUnidadInvisible = new Coordenada(0, 3);
        elMapa.colocarUnaUnidad(unaUnidadInvisible, coordenadaUnidadInvisible);

        // Creo un amo supremo en rango de la unidad invisible
        elMapa.colocarUnaUnidad(new AmoSupremo(), new Coordenada(0, 0));

        // Creo unidad auxiliar para atacar y matar a la unidad invisible
        Coordenada coordenadaAtacante = new Coordenada(0, 2);
        elMapa.colocarUnaUnidad(new Guardian(), coordenadaAtacante);
        for (int i = 0; i < 7; i++) {
            elMapa.atacar(coordenadaAtacante, coordenadaUnidadInvisible);
        }

        assertDoesNotThrow(() -> elMapa.colocarUnaUnidad(new AmoSupremo(), coordenadaUnidadInvisible));
    }
 */
}
