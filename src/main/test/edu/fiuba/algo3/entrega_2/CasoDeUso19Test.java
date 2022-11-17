package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoPuedeAtacarUnidadVoladora;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Mapa.*;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.*;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso19Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01SiUnZerglingIntentaAtacarAUnMutaliscoSeLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        Unidad unZerling = new Zerling();
        Unidad unMutalisco = new Mutalisco();
        Coordenada coordenadaZerling = new Coordenada(0,0);
        Coordenada coordenadaMutalisco = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieTerrestre(), coordenadaMutalisco);

        elMapa.colocarUnaUnidad(unZerling, coordenadaZerling);
        elMapa.colocarUnaUnidad(unMutalisco, coordenadaMutalisco);

        assertThrows(ErrorNoPuedeAtacarUnidadVoladora.class,
                () -> elMapa.atacar(coordenadaZerling, coordenadaMutalisco));
    }

    @Test
    public void test02SiUnZerglingIntentaAtacarAUnGuardianSeLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        Unidad unZerling = new Zerling();
        Unidad unGuardian = new Guardian();
        Coordenada coordenadaZerling = new Coordenada(0,0);
        Coordenada coordenadaGuardian = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaGuardian);

        elMapa.colocarUnaUnidad(unZerling, coordenadaZerling);
        elMapa.colocarUnaUnidad(unGuardian, coordenadaGuardian);

        assertThrows(ErrorNoPuedeAtacarUnidadVoladora.class,
                () -> elMapa.atacar(coordenadaZerling, coordenadaGuardian));
    }

    @Test
    public void test03SiUnZerglingIntentaAtacarAUnScoutSeLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        Unidad unZerling = new Zerling();
        Unidad unScout = new Scout();
        Coordenada coordenadaZerling = new Coordenada(0,0);
        Coordenada coordenadaScout = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaScout);

        elMapa.colocarUnaUnidad(unZerling, coordenadaZerling);
        elMapa.colocarUnaUnidad(unScout, coordenadaScout);

        assertThrows(ErrorNoPuedeAtacarUnidadVoladora.class,
                () -> elMapa.atacar(coordenadaZerling, coordenadaScout));
    }

    @Test
    public void test04SiUnZealotIntentaAtacarAUnMutaliscoSeLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        Unidad unZealot = new Zealot();
        Unidad unMutalisco = new Mutalisco();
        Coordenada coordenadaZealot = new Coordenada(0,0);
        Coordenada coordenadaMutalisco = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaMutalisco);

        elMapa.colocarUnaUnidad(unZealot, coordenadaZealot);
        elMapa.colocarUnaUnidad(unMutalisco, coordenadaMutalisco);

        assertThrows(ErrorNoPuedeAtacarUnidadVoladora.class,
                () -> elMapa.atacar(coordenadaZealot, coordenadaMutalisco));
    }

    @Test
    public void test05SiUnZealotIntentaAtacarAUnGuardianSeLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        Unidad unZealot = new Zealot();
        Unidad unGuardian = new Guardian();
        Coordenada coordenadaZealot = new Coordenada(0,0);
        Coordenada coordenadaGuardian = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaGuardian);

        elMapa.colocarUnaUnidad(unZealot, coordenadaZealot);
        elMapa.colocarUnaUnidad(unGuardian, coordenadaGuardian);

        assertThrows(ErrorNoPuedeAtacarUnidadVoladora.class,
                () -> elMapa.atacar(coordenadaZealot, coordenadaGuardian));
    }

    @Test
    public void test06SiUnZealotIntentaAtacarAUnScoutSeLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        Unidad unZealot = new Zealot();
        Unidad unScout = new Scout();
        Coordenada coordenadaZealot = new Coordenada(0,0);
        Coordenada coordenadaScout = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaScout);

        elMapa.colocarUnaUnidad(unZealot, coordenadaZealot);
        elMapa.colocarUnaUnidad(unScout, coordenadaScout);

        assertThrows(ErrorNoPuedeAtacarUnidadVoladora.class,
                () -> elMapa.atacar(coordenadaZealot, coordenadaScout));
    }

    @Test
    public void test07SiUnMutaliscoEnTierraPuedeAtacarAUnHidraliscoEnTierraYNoSeLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        Unidad unMutalisco = new Mutalisco();
        Unidad unHidralisco = new Hidralisco();
        Coordenada coordenadaMutalisco = new Coordenada(0,0);
        Coordenada coordenadaHidralisco = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unMutalisco, coordenadaMutalisco);
        elMapa.colocarUnaUnidad(unHidralisco, coordenadaHidralisco);

        assertDoesNotThrow(() -> elMapa.atacar(coordenadaMutalisco, coordenadaHidralisco));
    }
}
