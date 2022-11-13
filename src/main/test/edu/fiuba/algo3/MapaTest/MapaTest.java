package edu.fiuba.algo3.MapaTest;

import edu.fiuba.algo3.modelo.EdificioZerg.Criadero;
import edu.fiuba.algo3.modelo.EdificioZerg.Extractor;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeConstruirEdificioSobreOtroEdificio;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MapaTest {

    @Test
    public void test01PuedoObtenerLaUnicaInstanciaDeMapa(){

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        assertNotNull(elMapa);
    }

    @Test
    public void test02NoPuedoConstruirUnCriaderoEnUnaCasillaQueTieneUnEdificioEncima(){
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.construirEdificio(new Criadero(), new Coordenada(2,2));

        assertThrows(ErrorNoSePuedeConstruirEdificioSobreOtroEdificio.class, () -> elMapa.construirEdificio(new Criadero(), new Coordenada(2,2)));
    }

    /*
     * Si quiero atacar, voy a atacar a una coordenada => Me comunico con el mapa
     * Si quiero crear una unidad, voy a estar hablando desde Zerg o Protoss => No me comunico con el mapa
     * Si quiero crear un edificio, lo voy a crear sobre una coordenada => Me comunico con el mapa
     * */
    @Test
    public void test03NoPuedoConstruirUnCriaderoDondeHayUnVolcanDeGas(){
        Coordenada coordenada = new Coordenada(0,0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Criadero(), coordenada));
    }

    @Test
    public void test04NoPuedoConstruirUnCriaderoDondeHayUnNodoMineral(){
        Coordenada coordenada = new Coordenada(0,0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarMaterial(new MineralRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Criadero(), coordenada));
    }

    @Test
    public void test05NoPuedoConstruirUnExtractorDondeNoHayGas(){
        Coordenada coordenada = new Coordenada(0,0);
        Recurso gasDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Extractor(gasDelImperio), coordenada));
    }
}
