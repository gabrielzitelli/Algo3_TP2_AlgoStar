package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Excepciones.ErrorFuegoCompañero;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoPuedeAtacarUnidadVoladora;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Guardian;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnidadTest {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01NoPedeUnaUnidadAtacarALaDeSuMismoImperio(){
        Mapa elMapa = Mapa.obtener();
        Unidad unZerling = new Zerling();
        Unidad unGuardian = new Guardian();
        Coordenada coordenadaZerling = new Coordenada(0,0);
        Coordenada coordenadaGuardian = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaGuardian);

        elMapa.colocarUnaUnidad(unZerling, coordenadaZerling);
        elMapa.colocarUnaUnidad(unGuardian, coordenadaGuardian);

        assertThrows(ErrorFuegoCompañero.class,
                () -> elMapa.atacar(coordenadaZerling, coordenadaGuardian));
    }
}
