package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.*;
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

        elMapa.colocarOcupable(unaUnidad, coordenadaUnidad);
        elMapa.colocarOcupable(new Zerling(), coordenadaPrimerUnidad);
        elMapa.colocarOcupable(new Zerling(), coordenadaSegundaUnidad);
        elMapa.colocarOcupable(new Zerling(), coordenadaTercerUnidad);

        for (int i = 0; i < 5; i++) {
            elMapa.atacar(coordenadaUnidad, coordenadaPrimerUnidad);
            unaUnidad.pasarTurno();
        }
        for (int i = 0; i < 5; i++) {
            elMapa.atacar(coordenadaPrimerUnidad, coordenadaSegundaUnidad);
            unaUnidad.pasarTurno();
        }
        for (int i = 0; i < 5; i++) {
            elMapa.atacar(coordenadaSegundaUnidad, coordenadaTercerUnidad);
            unaUnidad.pasarTurno();
        }

        return unaUnidad;
    }

    //TODO Comente el test porque no se a quien pasarle el turno

    @Test
    public void test01UnZealotSeHaceInvisibleYNoPuedeSerAtacadoPorOtraUnidad() {
        Mapa elMapa = Mapa.obtener();

        // Coloco unidad invisible en rango del amo supremo
        Unidad unZealotInvisible = crearUnidadInvisible();
        Coordenada coordenadaZealotInvisible = new Coordenada(0, 3);
        elMapa.colocarOcupable(unZealotInvisible, coordenadaZealotInvisible);

        Unidad unMutalisco = new Mutalisco();
        Coordenada coordenadaAtacante = new Coordenada(0,2);
        elMapa.colocarOcupable(unMutalisco, coordenadaAtacante);
        // La nueva unidad intenta matar al zealot invisible
        for (int i = 0; i < 8; i++) {
            elMapa.atacar(coordenadaAtacante, coordenadaZealotInvisible);
            unMutalisco.pasarTurno();
        }

        // El zealot sigue vivo
        assertThrows(ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada.class,
                () -> elMapa.colocarOcupable(new Zealot(), coordenadaZealotInvisible));
    }


    @Test
    public void test02UnZealotSeHaceInvisiblePeroPuedeSerAtacadoPorEstarEnRangoDeUnAmoSupremo() {
        Mapa elMapa = Mapa.obtener();

        elMapa.colocarOcupable(new AmoSupremo(), new Coordenada(0, 0));

        // Coloco unidad invisible en rango del amo supremo
        Unidad unZealotInvisible = crearUnidadInvisible();
        Coordenada coordenadaZealotInvisible = new Coordenada(0, 3);
        elMapa.colocarOcupable(unZealotInvisible, coordenadaZealotInvisible);

        // Creo unidad auxiliar para atacar y matar a la unidad invisible
        Coordenada coordenadaAtacante = new Coordenada(0, 2);
        Unidad unGuardian = new Guardian();
        elMapa.colocarOcupable(unGuardian, coordenadaAtacante);
        for (int i = 0; i < 7; i++) {
            elMapa.atacar(coordenadaAtacante, coordenadaZealotInvisible);
            unGuardian.pasarTurno();
        }

        assertDoesNotThrow(() -> elMapa.colocarOcupable(new AmoSupremo(), coordenadaZealotInvisible));
    }
}
