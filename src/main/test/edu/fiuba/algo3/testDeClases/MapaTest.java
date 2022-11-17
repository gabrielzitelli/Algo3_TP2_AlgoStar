package edu.fiuba.algo3.testDeClases;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.*;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeConstruirEdificioSobreOtroEdificio;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapaTest {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01PuedoObtenerLaUnicaInstanciaDeMapa(){

        Mapa elMapa = Mapa.obtener();

        assertNotNull(elMapa);
    }

    @Test
    public void test02NoPuedoConstruirpilonEnUnaCasillaQueTieneUnEdificioEncima(){
        Mapa elMapa = Mapa.obtener();

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
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Pilon(), coordenada));
    }

    @Test
    public void test04NoPuedoConstruirpilonDondeHayUnNodoMineral(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);

        elMapa.colocarMaterial(new MineralRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Criadero(), coordenada));
    }

    @Test
    public void test05NoPuedoConstruirUnExtractorDondeNoHayGas(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Recurso gasDelImperio = new Recurso(0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Extractor(gasDelImperio), coordenada));
    }

    @Test
    public void test06NoPuedoConstruirUnExtractorDondeHayNodoMineral(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Recurso gasDelImperio = new Recurso(0);

        elMapa.colocarMaterial(new MineralRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Extractor(gasDelImperio), coordenada));
    }

    @Test
    public void test07NoPuedoConstruirUnNexoMineralDondeHayUnVolcanDeGas(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Recurso mineralesDelImperio = new Recurso(0);

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new NexoMineral(mineralesDelImperio), coordenada));
    }

    @Test
    public void test08NoPuedoConstruirUnNexoMineralDondeHayUnaCasillaVacia(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Recurso mineralesDelImperio = new Recurso(0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new NexoMineral(mineralesDelImperio), coordenada));
    }

    @Test
    public void test09PuedoConstruirUnNexoMineralDondeHayUnNodoMineral(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Recurso mineralesDelImperio = new Recurso(0);

        elMapa.colocarMaterial(new MineralRecolectable(),coordenada);

        assertDoesNotThrow(() -> elMapa.construirEdificio(new NexoMineral(mineralesDelImperio), coordenada));
    }

    @Test
    public void test10NoPuedoConstruirUnEspiralSinMoho(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Recurso gasDelImperio = new Recurso(0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Espiral(), coordenada));
    }

    @Test
    public void test11NoPuedoConstruirUnaGuaridaSinMoho(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Recurso gasDelImperio = new Recurso(0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Guarida(), coordenada));
    }

    @Test
    public void test12NoPuedoConstruirUnPuertoEstelarEnUnaCasillaQueNoEstaEnergizada() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new PuertoEstelar(), coordenada));
    }
    @Test
    public void test13NoPuedoConstruirUnAccesoEnUnaCasillaQueNoEstaEnergizada() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Acceso(), coordenada));
    }
    @Test
    public void test14NoPuedoConstruirUnAsimiladoerEnUnaCasillaQueNoEstaEnergizada() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Recurso gasDelImperio = new Recurso(0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Asimilador(gasDelImperio), coordenada));
    }

    @Test
    public void test15NoPuedoConstuirUnaReservaDeReproduccionDondeHayUnNodoMineral() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);

        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenada));
    }

    @Test
    public void test16NoPuedoConstruirUnaReservaDeReproduccionDondeHayUnVolcanDeGas() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);

        elMapa.colocarMaterial(new GasRecolectable(), coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenada));
    }

    @Test
    public void test17NoPuedoConstruirUnaReservaDeReproduccionEnUnaCasillaSinMoho() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenada));
    }

    @Test
    public void test18CreoCriaderoSeExpandeDespuesDeUnTurnoElMohoYPuedoCrearUnaReservaEnElNuevoMoho(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaConMoho = new Coordenada(0,1);
        Criadero unCriadero = new Criadero();
        elMapa.construirEdificio(unCriadero, coordenadaCriadero);

        //El criadero se termina de construir
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        //El moho se expande
        unCriadero.pasarTurno();

        assertDoesNotThrow(() -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenadaConMoho));
    }

    @Test
    public void test19CreoCriaderoSeExpandeDespuesDeUnTurnoElMohoYNoPuedoCrearUnaReservaFueraYLejosDelMohoExpandido(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaSinMohoExpandido = new Coordenada(0,99);
        Criadero unCriadero = new Criadero();
        elMapa.construirEdificio(unCriadero, coordenadaCriadero);

        //construyo el criadero
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        //El moho se expande
        unCriadero.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenadaSinMohoExpandido));
    }

    @Test
    public void test20CreoCriaderoSeExpandeDespuesDeUnTurnoElMohoYNoPuedoCrearUnaReservaFueraYEnElLimiteDelMohoExpandido(){
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaLimiteMoho = new Coordenada(0,6);
        Criadero unCriadero = new Criadero();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.construirEdificio(unCriadero, coordenadaCriadero);

        //construyo el criadero
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        //El moho se expande
        unCriadero.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenadaLimiteMoho));
    }

    @Test
    public void test21CreoCriaderoSeExpandeDespuesDeUnTurnoElMohoYNoPuedoCrearOtraReservaFueraYEnElLimiteDelMohoExpandido(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaLimiteMoho = new Coordenada(3,3);
        Criadero unCriadero = new Criadero();

        elMapa.construirEdificio(unCriadero, coordenadaCriadero);

        //construyo el criadero
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        //El moho se expande
        unCriadero.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenadaLimiteMoho));
    }

    @Test
    public void test22CreoCriaderoSeExpandeDespuesDeUnTurnoElMohoYPuedoCrearUnaReservaEnElMedioDelNuevoMoho(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaConMoho = new Coordenada(3,1);
        Criadero unCriadero = new Criadero();

        elMapa.construirEdificio(unCriadero, coordenadaCriadero);

        //construyo el criadero
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        //El moho se expande
        unCriadero.pasarTurno();

        assertDoesNotThrow(() -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenadaConMoho));
    }

    @Test
    public void test23CreoCriaderoSeExpandeDespuesDosVecesDespuesDeTresTurnosElMohoYPuedoCrearUnaReservaEnElNuevoMoho(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenadaConMoho = new Coordenada(0,6);
        Criadero unCriadero = new Criadero();

        elMapa.construirEdificio(unCriadero, coordenada);

        //construyo el criadero
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        //El moho se expande dos veces
        for (int i = 0; i < 3; i++)
            unCriadero.pasarTurno();

        assertDoesNotThrow(() -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenadaConMoho));
    }

    @Test
    public void test24CreoCriaderoSeExpandeDosVecesDespuesDeTresTurnosElMohoYNoPuedoCrearOtraReservaFueraYEnElLimiteDelMohoExpandido(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaLimiteMoho = new Coordenada(0,7);
        Criadero unCriadero = new Criadero();

        elMapa.construirEdificio(unCriadero, coordenadaCriadero);

        //construyo el criadero
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        //El moho se expande dos veces
        for (int i = 0; i < 3; i++)
            unCriadero.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenadaLimiteMoho));
    }

    @Test
    public void test25CreoUnPilonYPuedoColocarUnEdificioProtossEnElRangoEnergizado(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaPilon = new Coordenada(0,0);
        Coordenada coordenadaEnergizada = new Coordenada(0,3);
        Pilon unPilon = new Pilon();

        elMapa.construirEdificio(unPilon, coordenadaPilon);

        //construyo el pilon
        for (int i = 0; i < 5; i++)
            unPilon.pasarTurno();

        assertDoesNotThrow(() -> elMapa.construirEdificio(new Acceso(), coordenadaEnergizada));
    }

    @Test
    public void test26CreoUnPilonYNoPuedoColocarUnEdificioProtossEnElRangoExteriorAlEnergizado(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaPilon = new Coordenada(0,0);
        Coordenada coordenadaLimiteSinEnergia = new Coordenada(0,4);
        Pilon unPilon = new Pilon();

        elMapa.construirEdificio(unPilon, coordenadaPilon);

        //construyo el criadero
        for (int i = 0; i < 5; i++)
            unPilon.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
            () -> elMapa.construirEdificio(new Acceso(), coordenadaLimiteSinEnergia));
    }

    @Test
    public void test27PuedoCrearUnEdificioZergEnUnaZonaConMohoYEnergizada(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaPilon = new Coordenada(0,6);
        Coordenada coordenadaEnergizadaYConMoho = new Coordenada(0,4);
        Pilon unPilon = new Pilon();
        Criadero unCriadero = new Criadero();

        elMapa.construirEdificio(unPilon, coordenadaPilon);
        elMapa.construirEdificio(unCriadero, coordenadaCriadero);

        for (int i = 0; i < 5; i++){
            //Construyo el pilon y energizo la zona
            unPilon.pasarTurno();

            //Contruyo el criadero y expando el moho
            unCriadero.pasarTurno();
        }

        assertDoesNotThrow(() -> elMapa.construirEdificio(new Espiral(), coordenadaEnergizadaYConMoho));
    }

    @Test
    public void test28NoPuedoCrearUnEdificioProtossEnUnaZonaConMohoYEnergizada(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaPilon = new Coordenada(0,6);
        Coordenada coordenadaEnergizadaYConMoho = new Coordenada(0,4);
        Pilon unPilon = new Pilon();
        Criadero unCriadero = new Criadero();

        elMapa.construirEdificio(unPilon, coordenadaPilon);
        elMapa.construirEdificio(unCriadero, coordenadaCriadero);

        for (int i = 0; i < 5; i++){
            //Construyo el pilon y energizo la zona
            unPilon.pasarTurno();

            //Contruyo el criadero y expando el moho
            unCriadero.pasarTurno();
        }

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class ,
                () -> elMapa.construirEdificio(new PuertoEstelar(), coordenadaEnergizadaYConMoho));
    }
}
