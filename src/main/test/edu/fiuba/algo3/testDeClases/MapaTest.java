package edu.fiuba.algo3.testDeClases;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.*;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Excepciones.*;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.GasBruto;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Mapa.MineralBruto;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.*;

public class MapaTest {

    @BeforeEach
    public void setup() {
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01PuedoObtenerLaUnicaInstanciaDeMapa() {

        Mapa elMapa = Mapa.obtener();

        assertNotNull(elMapa);
    }

    @Test
    public void test02NoPuedoConstruirpilonEnUnaCasillaQueTieneUnEdificioEncima() {
        Mapa elMapa = Mapa.obtener();

        elMapa.construirEdificio(new Criadero(), new Coordenada(2, 2));

        assertThrows(ErrorNoSePuedeConstruirEdificioSobreOtroEdificio.class, () -> elMapa.construirEdificio(new Criadero(), new Coordenada(2, 2)));
    }

    /*
     * Si quiero atacar, voy a atacar a una coordenada => Me comunico con el mapa
     * Si quiero crear una unidad, voy a estar hablando desde Zerg o Protoss => No me comunico con el mapa
     * Si quiero crear un edificio, lo voy a crear sobre una coordenada => Me comunico con el mapa
     * */
    @Test
    public void test03NoPuedoConstruirpilonDondeHayUnVolcanDeGas() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);

        elMapa.colocarMaterial(new GasRecolectable(), coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Pilon(), coordenada));
    }

    @Test
    public void test04NoPuedoConstruirpilonDondeHayUnNodoMineral() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);

        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Criadero(), coordenada));
    }

    @Test
    public void test05NoPuedoConstruirUnExtractorDondeNoHayGas() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);
        Recurso gasDelImperio = new Gas(0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Extractor(gasDelImperio), coordenada));
    }

    @Test
    public void test06NoPuedoConstruirUnExtractorDondeHayNodoMineral() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);
        Recurso gasDelImperio = new Recurso(0);

        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Extractor(gasDelImperio), coordenada));
    }

    @Test
    public void test07NoPuedoConstruirUnNexoMineralDondeHayUnVolcanDeGas() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);
        Recurso mineralesDelImperio = new Recurso(0);

        elMapa.colocarMaterial(new GasRecolectable(), coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new NexoMineral(mineralesDelImperio), coordenada));
    }

    @Test
    public void test08NoPuedoConstruirUnNexoMineralDondeHayUnaCasillaVacia() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);
        Recurso mineralesDelImperio = new Recurso(0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new NexoMineral(mineralesDelImperio), coordenada));
    }

    @Test
    public void test09PuedoConstruirUnNexoMineralDondeHayUnNodoMineral() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);
        Recurso mineralesDelImperio = new Recurso(0);

        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        assertDoesNotThrow(() -> elMapa.construirEdificio(new NexoMineral(mineralesDelImperio), coordenada));
    }

    @Test
    public void test10NoPuedoConstruirUnEspiralSinMoho() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Espiral(), coordenada));
    }

    @Test
    public void test11NoPuedoConstruirUnaGuaridaSinMoho() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Guarida(), coordenada));
    }

    @Test
    public void test12NoPuedoConstruirUnPuertoEstelarEnUnaCasillaQueNoEstaEnergizada() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new PuertoEstelar(), coordenada));
    }

    @Test
    public void test13NoPuedoConstruirUnAccesoEnUnaCasillaQueNoEstaEnergizada() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Acceso(), coordenada));
    }

    @Test
    public void test14NoPuedoConstruirUnAsimiladoerEnUnaCasillaQueNoEstaEnergizada() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);
        Recurso gasDelImperio = new Recurso(0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Asimilador(gasDelImperio), coordenada));
    }

    @Test
    public void test15NoPuedoConstuirUnaReservaDeReproduccionDondeHayUnNodoMineral() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);

        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenada));
    }

    @Test
    public void test16NoPuedoConstruirUnaReservaDeReproduccionDondeHayUnVolcanDeGas() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);

        elMapa.colocarMaterial(new GasRecolectable(), coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenada));
    }

    @Test
    public void test17NoPuedoConstruirUnaReservaDeReproduccionEnUnaCasillaSinMoho() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenada));
    }

    @Test
    public void test18CreoCriaderoSeExpandeDespuesDeUnTurnoElMohoYPuedoCrearUnaReservaEnElNuevoMoho() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0, 0);
        Coordenada coordenadaConMoho = new Coordenada(0, 1);
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
    public void test19CreoCriaderoSeExpandeDespuesDeUnTurnoElMohoYNoPuedoCrearUnaReservaFueraYLejosDelMohoExpandido() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0, 0);
        Coordenada coordenadaSinMohoExpandido = new Coordenada(0, 99);
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
    public void test20CreoCriaderoSeExpandeDespuesDeUnTurnoElMohoYNoPuedoCrearUnaReservaFueraYEnElLimiteDelMohoExpandido() {
        Coordenada coordenadaCriadero = new Coordenada(0, 0);
        Coordenada coordenadaLimiteMoho = new Coordenada(0, 6);
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
    public void test21CreoCriaderoSeExpandeDespuesDeUnTurnoElMohoYNoPuedoCrearOtraReservaFueraYEnElLimiteDelMohoExpandido() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0, 0);
        Coordenada coordenadaLimiteMoho = new Coordenada(3, 3);
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
    public void test22CreoCriaderoSeExpandeDespuesDeUnTurnoElMohoYPuedoCrearUnaReservaEnElMedioDelNuevoMoho() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0, 0);
        Coordenada coordenadaConMoho = new Coordenada(3, 1);
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
    public void test23CreoCriaderoSeExpandeDespuesDosVecesDespuesDeTresTurnosElMohoYPuedoCrearUnaReservaEnElNuevoMoho() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);
        Coordenada coordenadaConMoho = new Coordenada(0, 6);
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
    public void test24CreoCriaderoSeExpandeDosVecesDespuesDeTresTurnosElMohoYNoPuedoCrearOtraReservaFueraYEnElLimiteDelMohoExpandido() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0, 0);
        Coordenada coordenadaLimiteMoho = new Coordenada(0, 7);
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
    public void test25CreoUnPilonYPuedoColocarUnEdificioProtossEnElRangoEnergizado() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaPilon = new Coordenada(0, 0);
        Coordenada coordenadaEnergizada = new Coordenada(0, 3);
        Pilon unPilon = new Pilon();
        unPilon.asignarSuministro(new Suministro(0));

        elMapa.construirEdificio(unPilon, coordenadaPilon);

        //construyo el pilon
        for (int i = 0; i < 5; i++)
            unPilon.pasarTurno();

        assertDoesNotThrow(() -> elMapa.construirEdificio(new Acceso(), coordenadaEnergizada));
    }

    @Test
    public void test26CreoUnPilonYNoPuedoColocarUnEdificioProtossEnElRangoExteriorAlEnergizado() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaPilon = new Coordenada(0, 0);
        Coordenada coordenadaLimiteSinEnergia = new Coordenada(0, 4);
        Pilon unPilon = new Pilon();
        unPilon.asignarSuministro(new Suministro(0));

        elMapa.construirEdificio(unPilon, coordenadaPilon);

        //construyo el criadero
        for (int i = 0; i < 5; i++)
            unPilon.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Acceso(), coordenadaLimiteSinEnergia));
    }

    @Test
    public void test27PuedoCrearUnEdificioZergEnUnaZonaConMohoYEnergizada() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0, 0);
        Coordenada coordenadaPilon = new Coordenada(0, 6);
        Coordenada coordenadaEnergizadaYConMoho = new Coordenada(0, 4);
        Pilon unPilon = new Pilon();
        Criadero unCriadero = new Criadero();
        unPilon.asignarSuministro(new Suministro(0));

        elMapa.construirEdificio(unPilon, coordenadaPilon);
        elMapa.construirEdificio(unCriadero, coordenadaCriadero);

        for (int i = 0; i < 5; i++) {
            //Construyo el pilon y energizo la zona
            unPilon.pasarTurno();

            //Contruyo el criadero y expando el moho
            unCriadero.pasarTurno();
        }

        assertDoesNotThrow(() -> elMapa.construirEdificio(new Espiral(), coordenadaEnergizadaYConMoho));
    }

    @Test
    public void test28NoPuedoCrearUnEdificioProtossEnUnaZonaConMohoYEnergizada() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0, 0);
        Coordenada coordenadaPilon = new Coordenada(0, 6);
        Coordenada coordenadaEnergizadaYConMoho = new Coordenada(0, 4);
        Pilon unPilon = new Pilon();
        Criadero unCriadero = new Criadero();
        unPilon.asignarSuministro(new Suministro(0));

        elMapa.construirEdificio(unPilon, coordenadaPilon);
        elMapa.construirEdificio(unCriadero, coordenadaCriadero);

        for (int i = 0; i < 5; i++) {
            //Construyo el pilon y energizo la zona
            unPilon.pasarTurno();

            //Contruyo el criadero y expando el moho
            unCriadero.pasarTurno();
        }

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new PuertoEstelar(), coordenadaEnergizadaYConMoho));
    }

    @Test
    public void test29ColocarUnidadEnCasillaOcupadaLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        Criadero unCriadero = new Criadero();
        Coordenada coordenada = new Coordenada(0, 0);

        elMapa.construirEdificio(unCriadero, coordenada);

        Zangano unZangano = new Zangano();

        assertThrows(ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada.class,
                () -> elMapa.colocarUnaUnidad(unZangano, coordenada));
    }

    @Test
    public void test30AtacarUnaCasillaVaciaLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaAtacante = new Coordenada(0, 0);
        Coordenada coordenadaAtacada = new Coordenada(1, 0);

        assertThrows(ErrorUnaCasillaVaciaNoPuedeParticiparEnAtaque.class,
                () -> elMapa.atacar(coordenadaAtacante, coordenadaAtacada));
    }

    @Test
    public void test31DesconstruirEdificioDeUnaCasillaVaciaLanzaExcepcion() {
        assertThrows(ErrorNoSePuedeDesconstruirUnEdificioNoCreado.class,
                () -> Mapa.obtener().destruirEdificio(new Coordenada(0, 0)));
    }

    @Test
    public void test32ConseguirEdificioDeUnaCasillaVaciaLanzaExcepcion() {
        assertThrows(ErrorNoExisteNingunEdificioEnEstaCasilla.class,
                () -> Mapa.obtener().obtenerEdificio(new Coordenada(0, 0)));
    }

    @Test
    public void test33ElMapaComienzaConMinerales() {
        Mapa elMapa = Mapa.obtener();
        elMapa.recolocarBasesIniciales();

        LinkedList<Casilla> listaMinerales = elMapa.obtenerMinerales();

        for (Casilla casillaMineral : listaMinerales)
            assertEquals(casillaMineral.obtenerMaterial().getClass(), MineralBruto.class);

        assertTrue(listaMinerales.size() > 0);
    }

    @Test
    public void test34ElMapaComienzaConVolcanesDeGas() {
        Mapa elMapa = Mapa.obtener();
        elMapa.recolocarBasesIniciales();

        LinkedList<Casilla> listaVolcanes = elMapa.obtenerVolcanesDeGas();

        for (Casilla casillaGas : listaVolcanes)
            assertEquals(casillaGas.obtenerMaterial().getClass(), GasBruto.class);

        assertTrue(listaVolcanes.size() > 0);
    }

    @Test
    public void test35LaMitadSuperiorDelMapaTieneMinerales() {
        Mapa elMapa = Mapa.obtener();
        elMapa.recolocarBasesIniciales();

        LinkedList<Casilla> listaMineralesMitadSuperior = new LinkedList<>();
        LinkedList<Casilla> listaMinerales = elMapa.obtenerMinerales();

        for (Casilla casillaMineral : listaMinerales) {
            int xMineral = casillaMineral.obtenerCoordenada().getCoordenadaX();
            if (xMineral < 50)
                listaMineralesMitadSuperior.push(casillaMineral);
        }

        assertTrue(listaMineralesMitadSuperior.size() > 0);
    }

    @Test
    public void test36LaMitadInferiorDelMapaTieneMinerales() {
        Mapa elMapa = Mapa.obtener();
        elMapa.recolocarBasesIniciales();

        LinkedList<Casilla> listaMineralesMitadInferior = new LinkedList<>();
        LinkedList<Casilla> listaMinerales = elMapa.obtenerMinerales();

        for (Casilla casillaMineral : listaMinerales) {
            int xMineral = casillaMineral.obtenerCoordenada().getCoordenadaX();
            if (xMineral > 50)
                listaMineralesMitadInferior.push(casillaMineral);
        }

        assertTrue(listaMineralesMitadInferior.size() > 0);
    }

    @Test
    public void test37LaCantidadDeMineralesDeLasMitadesSonSimilares() {
        Mapa elMapa = Mapa.obtener();
        elMapa.recolocarBasesIniciales();

        int cantidadDeMineralesPorBase = 7;
        int maximoDeBasesDeDiferenciaAceptable = 1;
        LinkedList<Casilla> listaMineralesMitadSuperior = new LinkedList<>();
        LinkedList<Casilla> listaMineralesMitadInferior = new LinkedList<>();
        LinkedList<Casilla> listaMinerales = elMapa.obtenerMinerales();

        for (Casilla casillaMineral : listaMinerales) {
            int xMineral = casillaMineral.obtenerCoordenada().getCoordenadaX();
            if (xMineral < 50)
                listaMineralesMitadSuperior.push(casillaMineral);
            else if (xMineral > 50)
                listaMineralesMitadInferior.push(casillaMineral);
        }

        int diferenciaDeMinerales = abs(listaMineralesMitadSuperior.size() - listaMineralesMitadInferior.size());

        assertTrue(diferenciaDeMinerales < (maximoDeBasesDeDiferenciaAceptable * cantidadDeMineralesPorBase));
    }

    @Test
    public void test38LaMitadSuperiorDelMapaTieneVolcanesDeGas() {
        Mapa elMapa = Mapa.obtener();
        elMapa.recolocarBasesIniciales();

        LinkedList<Casilla> listaVolcanesMitadSuperior = new LinkedList<>();
        LinkedList<Casilla> listaVolcanes = elMapa.obtenerVolcanesDeGas();

        for (Casilla casillaGas : listaVolcanes) {
            int xMineral = casillaGas.obtenerCoordenada().getCoordenadaX();
            if (xMineral < 50)
                listaVolcanesMitadSuperior.push(casillaGas);
        }

        assertTrue(listaVolcanesMitadSuperior.size() > 0);
    }

    @Test
    public void test39LaMitadInferiorDelMapaTieneVolcanesDeGas(){
        Mapa elMapa = Mapa.obtener();
        elMapa.recolocarBasesIniciales();

        LinkedList<Casilla> listaVolcanesMitadInferior = new LinkedList<>();
        LinkedList<Casilla> listaVolcanes = elMapa.obtenerVolcanesDeGas();

        for(Casilla casillaGas: listaVolcanes){
            int xGas = casillaGas.obtenerCoordenada().getCoordenadaX();
            if(xGas > 50)
                listaVolcanesMitadInferior.push(casillaGas);
        }

        assertTrue(listaVolcanesMitadInferior.size() > 0);
    }

    @Test
    public void test40LaCantidadDeVolcanesDeGasDeLasMitadesSonSimilares() {
        Mapa elMapa = Mapa.obtener();
        elMapa.recolocarBasesIniciales();

        int cantidadDeVolcanesPorBase = 1;
        int maximoDeBasesDeDiferenciaAceptable = 1;
        LinkedList<Casilla> listaVolcanesMitadSuperior = new LinkedList<>();
        LinkedList<Casilla> listaVolcanesMitadInferior = new LinkedList<>();
        LinkedList<Casilla> listaVolcanes = elMapa.obtenerVolcanesDeGas();

        for (Casilla casillaGas : listaVolcanes) {
            int xGas = casillaGas.obtenerCoordenada().getCoordenadaX();
            if (xGas < 50)
                listaVolcanesMitadSuperior.push(casillaGas);
            else if (xGas > 50)
                listaVolcanesMitadInferior.push(casillaGas);
        }

        int diferenciaDeVolcanes = abs(listaVolcanesMitadSuperior.size() - listaVolcanesMitadInferior.size());

        assertTrue(diferenciaDeVolcanes < (maximoDeBasesDeDiferenciaAceptable * cantidadDeVolcanesPorBase));
    }

    @Test
    public void test41NoPuedoColocarUnEdificioZergEnTerrenoEspacial(){
        Mapa elMapa = Mapa.obtener();
        elMapa.recolocarBasesIniciales();
        //elMapa posee terreno espacial en el (0,0)

        Criadero unCriadero = new Criadero();
        Coordenada coordenadaEspacial = new Coordenada(0, 0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(unCriadero, coordenadaEspacial));
    }

    @Test
    public void test42NoPuedoColocarUnEdificioProtossEnTerrenoEspacial(){
        Mapa elMapa = Mapa.obtener();
        elMapa.recolocarBasesIniciales();
        //elMapa posee terreno espacial en el (0,0)

        Pilon unPilon = new Pilon();
        Coordenada coordenadaEspacial = new Coordenada(0, 0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(unPilon, coordenadaEspacial));
    }
}
