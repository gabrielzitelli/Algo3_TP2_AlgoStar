package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoPuedeAtacarUnidadVoladora;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.UnidadesProtoss.Scout;
import edu.fiuba.algo3.modelo.UnidadesProtoss.Zealot;
import edu.fiuba.algo3.modelo.UnidadesZerg.Guardian;
import edu.fiuba.algo3.modelo.UnidadesZerg.Mutalisco;
import edu.fiuba.algo3.modelo.UnidadesZerg.Zerling;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso19Test {

    @Test
    public void test01SiUnZerglingIntentaAtacarAUnMutaliscoSeLanzaExcepcion() {
        Zerling unZerling = new Zerling();
        Mutalisco unMutalisco = new Mutalisco();

        Coordenada coordenadaZerling = new Coordenada(0,0);
        Coordenada coordenadaMutalisco = new Coordenada(0,1);

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarSuperficie(new SuperficieTerrestre(), coordenadaZerling);
        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaMutalisco);

        elMapa.colocarUnaUnidad(unZerling, coordenadaZerling);
        elMapa.colocarUnaUnidad(unMutalisco, coordenadaMutalisco);

        assertThrows(ErrorNoPuedeAtacarUnidadVoladora.class,
                () -> elMapa.atacar(coordenadaZerling, coordenadaMutalisco));
    }

    @Test
    public void test02SiUnZerglingIntentaAtacarAUnGuardianSeLanzaExcepcion() {
        Zerling unZerling = new Zerling();
        Guardian unGuardian = new Guardian();

        Coordenada coordenadaZerling = new Coordenada(0,0);
        Coordenada coordenadaGuardian = new Coordenada(0,1);

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarSuperficie(new SuperficieTerrestre(), coordenadaZerling);
        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaGuardian);

        elMapa.colocarUnaUnidad(unZerling, coordenadaZerling);
        elMapa.colocarUnaUnidad(unGuardian, coordenadaGuardian);

        assertThrows(ErrorNoPuedeAtacarUnidadVoladora.class,
                () -> elMapa.atacar(coordenadaZerling, coordenadaGuardian));
    }

    @Test
    public void test03SiUnZerglingIntentaAtacarAUnScoutSeLanzaExcepcion() {
        Zerling unZerling = new Zerling();
        Scout unScout = new Scout();

        Coordenada coordenadaZerling = new Coordenada(0,0);
        Coordenada coordenadaScout = new Coordenada(0,1);

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarSuperficie(new SuperficieTerrestre(), coordenadaZerling);
        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaScout);

        elMapa.colocarUnaUnidad(unZerling, coordenadaZerling);
        elMapa.colocarUnaUnidad(unScout, coordenadaScout);

        assertThrows(ErrorNoPuedeAtacarUnidadVoladora.class,
                () -> elMapa.atacar(coordenadaZerling, coordenadaScout));
    }

    @Test
    public void test04SiUnZealotIntentaAtacarAUnMutaliscoSeLanzaExcepcion() {
        Zealot unZealot = new Zealot();
        Mutalisco unMutalisco = new Mutalisco();

        Coordenada coordenadaZealot = new Coordenada(0,0);
        Coordenada coordenadaMutalisco = new Coordenada(0,1);

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarSuperficie(new SuperficieTerrestre(), coordenadaZealot);
        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaMutalisco);

        elMapa.colocarUnaUnidad(unZealot, coordenadaZealot);
        elMapa.colocarUnaUnidad(unMutalisco, coordenadaMutalisco);

        assertThrows(ErrorNoPuedeAtacarUnidadVoladora.class,
                () -> elMapa.atacar(coordenadaZealot, coordenadaMutalisco));
    }

    @Test
    public void test05SiUnZealotIntentaAtacarAUnGuardianSeLanzaExcepcion() {
        Zealot unZealot = new Zealot();
        Guardian unGuardian = new Guardian();

        Coordenada coordenadaZealot = new Coordenada(0,0);
        Coordenada coordenadaGuardian = new Coordenada(0,1);

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarSuperficie(new SuperficieTerrestre(), coordenadaZealot);
        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaGuardian);

        elMapa.colocarUnaUnidad(unZealot, coordenadaZealot);
        elMapa.colocarUnaUnidad(unGuardian, coordenadaGuardian);

        assertThrows(ErrorNoPuedeAtacarUnidadVoladora.class,
                () -> elMapa.atacar(coordenadaZealot, coordenadaGuardian));
    }

    @Test
    public void test06SiUnZealotIntentaAtacarAUnScoutSeLanzaExcepcion() {
        Zealot unZealot = new Zealot();
        Scout unScout = new Scout();

        Coordenada coordenadaZealot = new Coordenada(0,0);
        Coordenada coordenadaScout = new Coordenada(0,1);

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarSuperficie(new SuperficieTerrestre(), coordenadaZealot);
        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaScout);

        elMapa.colocarUnaUnidad(unZealot, coordenadaZealot);
        elMapa.colocarUnaUnidad(unScout, coordenadaScout);

        assertThrows(ErrorNoPuedeAtacarUnidadVoladora.class,
                () -> elMapa.atacar(coordenadaZealot, coordenadaScout));
    }
}
