package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Guardian;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Mutalisco;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GuardianTest {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01PuedoCrearUnGuardian() {
        Guardian unGuardian = new Guardian();

        assertNotNull(unGuardian);
    }

    @Test
    public void test02UnGuardianPuedeDaniarAUnaUnidadTerrestre() {
        Mapa elMapa = Mapa.obtener();

        Guardian unGuardian = new Guardian();
        Zerling unZerling = new Zerling();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unGuardian, coordenadaAtacante);
        elMapa.colocarUnaUnidad(unZerling, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }

    @Test
    public void test03UnGuardianPuedeRecibirDanioDeUnaUnidadAerea() {
        Mapa elMapa = Mapa.obtener();

        Guardian unGuardian = new Guardian();
        Mutalisco unMutalisco = new Mutalisco();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unMutalisco, coordenadaAtacante);
        elMapa.colocarUnaUnidad(unGuardian, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.atacar(coordenadaAtacante, coordenadaAtacado));
    }
}
