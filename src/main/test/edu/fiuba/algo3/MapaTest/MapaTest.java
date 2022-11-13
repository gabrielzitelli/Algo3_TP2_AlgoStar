package edu.fiuba.algo3.MapaTest;

import edu.fiuba.algo3.modelo.EdificioProtoss.Acceso;
import edu.fiuba.algo3.modelo.EdificioProtoss.Asimilador;
import edu.fiuba.algo3.modelo.EdificioProtoss.NexoMineral;
import edu.fiuba.algo3.modelo.EdificioProtoss.PuertoEstelar;
import edu.fiuba.algo3.modelo.EdificioZerg.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeConstruirEdificioSobreOtroEdificio;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void test06NoPuedoConstruirUnExtractorDondeHayNodoMineral(){
        Coordenada coordenada = new Coordenada(0,0);
        Recurso gasDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarMaterial(new MineralRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Extractor(gasDelImperio), coordenada));
    }

    @Test
    public void test07NoPuedoConstruirUnNexoMineralDondeHayUnVolcanDeGas(){
        Coordenada coordenada = new Coordenada(0,0);
        Recurso mineralesDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new NexoMineral(mineralesDelImperio), coordenada));
    }

    @Test
    public void test08NoPuedoConstruirUnNexoMineralDondeHayUnaCasillaVacia(){
        Coordenada coordenada = new Coordenada(0,0);
        Recurso mineralesDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new NexoMineral(mineralesDelImperio), coordenada));
    }

    @Test
    public void test09PuedoConstruirUnNexoMineralDondeHayUnNodoMineral(){
        Coordenada coordenada = new Coordenada(0,0);
        Recurso mineralesDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarMaterial(new MineralRecolectable(),coordenada);

        assertDoesNotThrow(() -> elMapa.construirEdificio(new NexoMineral(mineralesDelImperio), coordenada));
    }

    @Test
    public void test10NoPuedoConstruirUnEspiralSinMoho(){
        Coordenada coordenada = new Coordenada(0,0);
        Recurso gasDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Espiral(), coordenada));
    }

    @Test
    public void test11NoPuedoConstruirUnaGuaridaSinMoho(){
        Coordenada coordenada = new Coordenada(0,0);
        Recurso gasDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Guarida(), coordenada));
    }

    @Test
    public void test12NoPuedoConstruirUnPuertoEstelarEnUnaCasillaQueNoEstaEnergizada() {
        Coordenada coordenada = new Coordenada(0,0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new PuertoEstelar(), coordenada));
    }
    @Test
    public void test13NoPuedoConstruirUnAccesoEnUnaCasillaQueNoEstaEnergizada() {
        Coordenada coordenada = new Coordenada(0,0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Acceso(), coordenada));
    }
    @Test
    public void test14NoPuedoConstruirUnAsimiladoerEnUnaCasillaQueNoEstaEnergizada() {
        Coordenada coordenada = new Coordenada(0,0);
        Recurso gasDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Asimilador(gasDelImperio), coordenada));
    }

    @Test
    public void test15NoPuedoConstuirUnaReservaDeReproduccionDondeHayUnNodoMineral() {
        Coordenada coordenada = new Coordenada(0,0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenada));
    }

    @Test
    public void test16NoPuedoConstruirUnaReservaDeReproduccionDondeHayUnVolcanDeGas() {
        Coordenada coordenada = new Coordenada(0,0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarMaterial(new GasRecolectable(), coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenada));
    }

    @Test
    public void test17NoPuedoConstruirUnaReservaDeReproduccionEnUnaCasillaSinMoho() {
        Coordenada coordenada = new Coordenada(0,0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenada));
    }
    
}
