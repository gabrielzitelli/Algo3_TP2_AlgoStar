package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.AmoSupremo;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Guardian;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso28Test {

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
    public void test01UnZealotSeHaceInvisibleYNoPuedeSerAtacadoPorOtraUnidad() {
        Mapa elMapa = Mapa.obtener();

        Coordenada coordenadaZealot = new Coordenada(0,0);
        Coordenada coordenadaPrimerUnidad = new Coordenada(0,1);
        Coordenada coordenadaSegundaUnidad = new Coordenada(0,2);
        Coordenada coordenadaTercerUnidad = new Coordenada(0,3);

        elMapa.colocarUnaUnidad(new Zealot(), coordenadaZealot);
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
        // La nueva unidad intenta matar al zealot invisible
        for (int i = 0; i < 8; i++) {
            elMapa.atacar(coordenadaAtacante, coordenadaTercerUnidad);
        }

        // El zealot sigue vivo
        assertThrows(ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada.class,
                () -> elMapa.colocarUnaUnidad(new Zealot(), coordenadaTercerUnidad));
    }

    @Test
    public void test02UnZealotSeHaceInvisiblePeroPuedeSerAtacadoPorEstarEnRangoDeUnAmoSupremo() {
        Mapa elMapa = Mapa.obtener();

        elMapa.colocarUnaUnidad(new AmoSupremo(), new Coordenada(0, 0));

        // Coloco unidad invisible en rango del amo supremo
        Unidad unZealotInvisible = crearUnidadInvisible();
        Coordenada coordenadaZealotInvisible = new Coordenada(0, 3);
        elMapa.colocarUnaUnidad(unZealotInvisible, coordenadaZealotInvisible);

        // Creo unidad auxiliar para atacar y matar a la unidad invisible
        Coordenada coordenadaAtacante = new Coordenada(0, 2);
        elMapa.colocarUnaUnidad(new Guardian(), coordenadaAtacante);
        for (int i = 0; i < 7; i++) {
            elMapa.atacar(coordenadaAtacante, coordenadaZealotInvisible);
        }

        assertDoesNotThrow(() -> elMapa.colocarUnaUnidad(new AmoSupremo(), coordenadaZealotInvisible));
    }
}
