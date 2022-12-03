package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnidadTest {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void Test01prueboElMetodoEstaDentroDelRadio(){

        Mapa elMapa = Mapa.obtener();
        Unidad unaUnidad = new Dragon(); // RANGO DE ATAQUE 4

        Coordenada coordenadaUnidad = new Coordenada(0,0);

        elMapa.colocarUnaUnidad(unaUnidad, coordenadaUnidad);

        Coordenada coordenadaTest = new Coordenada( 2,2);

        assertTrue(unaUnidad.estaDentroDelRadio(coordenadaTest));

    }

    @Test
    public void Test02prueboElMetodoEstaDentroDelRadioPeroCoordenadaDesplazada(){

        Mapa elMapa = Mapa.obtener();
        Unidad unaUnidad = new Dragon(); // RANGO DE ATAQUE 4

        Coordenada coordenadaUnidad = new Coordenada(67,44);

        elMapa.colocarUnaUnidad(unaUnidad, coordenadaUnidad);

        Coordenada coordenadaTest = new Coordenada( 69,46);

        assertTrue(unaUnidad.estaDentroDelRadio(coordenadaTest));

    }
}
