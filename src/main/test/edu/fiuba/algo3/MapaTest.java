package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.EdificioProtoss.*;
import edu.fiuba.algo3.modelo.EdificioZerg.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeConstruirEdificioSobreOtroEdificio;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.Test;

import javax.crypto.CipherInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class MapaTest {

    @Test
    public void test01PuedoObtenerLaUnicaInstanciaDeMapa(){

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        assertNotNull(elMapa);
    }

    @Test
    public void test02NoPuedoConstruirpilonEnUnaCasillaQueTieneUnEdificioEncima(){
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
    public void test03NoPuedoConstruirpilonDondeHayUnVolcanDeGas(){
        Coordenada coordenada = new Coordenada(0,0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Criadero(), coordenada));
    }

    @Test
    public void test04NoPuedoConstruirpilonDondeHayUnNodoMineral(){
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

    @Test
    public void test18CreopilonSeExpandeDespuesDeUnTurnoElMohoYPuedoCrearUnaReservaEnElNuevoMoho(){
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenada2 = new Coordenada(0,1);
        Criadero pilon = new Criadero();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.construirEdificio(pilon, coordenada);
        //El criadero se termina de construir
        for (int i = 0; i < 4; i++){
            pilon.pasarTurno();
        }
        pilon.pasarTurno();

        assertDoesNotThrow(() -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenada2));
    }

    @Test
    public void test19CreopilonSeExpandeDespuesDeUnTurnoElMohoYPuedoCrearUnaReservaEnElNuevoMoho(){
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenada2 = new Coordenada(0,99);
        Criadero pilon = new Criadero();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.construirEdificio(pilon, coordenada);
        //construyo el criadero
        for (int i = 0; i < 4; i++){
            pilon.pasarTurno();
        }
        pilon.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class, () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenada2));
    }

    @Test
    public void test20CreopilonSeExpandeDespuesDeUnTurnoElMohoYPuedoCrearUnaReservaEnElNuevoMoho(){
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenada2 = new Coordenada(0,6);
        Criadero pilon = new Criadero();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.construirEdificio(pilon, coordenada);
        //construyo el criadero
        for (int i = 0; i < 4; i++){
            pilon.pasarTurno();
        }
        pilon.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class, () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenada2));
    }

    @Test
    public void test21CreopilonSeExpandeDespuesDeUnTurnoElMohoYPuedoCrearUnaReservaEnElNuevoMoho(){
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenada2 = new Coordenada(3,1);
        Criadero pilon = new Criadero();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.construirEdificio(pilon, coordenada);
        //construyo el criadero
        for (int i = 0; i < 4; i++){
            pilon.pasarTurno();
        }
        pilon.pasarTurno();

        assertDoesNotThrow(() -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenada2));
    }

    @Test
    public void test22CreopilonSeExpandeDespuesDeUnTurnoElMohoYPuedoCrearUnaReservaEnElNuevoMoho(){
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenada2 = new Coordenada(3,3);
        Criadero pilon = new Criadero();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.construirEdificio(pilon, coordenada);
        //construyo el criadero
        for (int i = 0; i < 4; i++){
            pilon.pasarTurno();
        }
        pilon.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class, () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenada2));
    }

    @Test
    public void test23CreopilonSeExpandeDespuesDeUnTurnoElMohoYPuedoCrearUnaReservaEnElNuevoMoho(){
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenada2 = new Coordenada(0,6);
        Criadero pilon = new Criadero();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.construirEdificio(pilon, coordenada);
        //construyo el criadero
        for (int i = 0; i < 4; i++){
            pilon.pasarTurno();
        }
        pilon.pasarTurno();
        pilon.pasarTurno();
        pilon.pasarTurno();

        assertDoesNotThrow(() -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenada2));
    }

    @Test
    public void test24CreopilonSeExpandeDespuesDeUnTurnoElMohoYPuedoCrearUnaReservaEnElNuevoMoho(){
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenada2 = new Coordenada(0,7);
        Criadero pilon = new Criadero();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.construirEdificio(pilon, coordenada);
        //construyo el criadero
        for (int i = 0; i < 4; i++){
            pilon.pasarTurno();
        }
        pilon.pasarTurno();
        pilon.pasarTurno();
        pilon.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class, () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenada2));
    }
    @Test
    public void test25CreoUnPilonYPuedoColocarUnEdificioProtossEnElRangoEnergizado(){
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenada2 = new Coordenada(0,3);
        Pilon pilon = new Pilon();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.construirEdificio(pilon, coordenada);
        //construyo el criadero
        for (int i = 0; i < 5; i++){
            pilon.pasarTurno();
        }
        assertDoesNotThrow(() -> elMapa.construirEdificio(new Acceso(), coordenada2));
    }
    @Test
    public void test25CreoUnPilonYNoPuedoColocarUnEdificioProtossEnElRangoExteriorAlEnergizado(){
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenada2 = new Coordenada(0,4);
        Pilon pilon = new Pilon();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.construirEdificio(pilon, coordenada);
        //construyo el criadero
        for (int i = 0; i < 5; i++){
            pilon.pasarTurno();
        }
        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class, () -> elMapa.construirEdificio(new Acceso(), coordenada2));
    }

    @Test
    public void test26(){
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaPilon = new Coordenada(0,4);
    }
}
