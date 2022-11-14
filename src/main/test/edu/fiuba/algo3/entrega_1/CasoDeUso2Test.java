package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.EdificioProtoss.*;
import edu.fiuba.algo3.modelo.EdificioZerg.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeHaConstruidoElExtractorSobreUnaCasilla;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.UnidadesZerg.UnidadZerg;
import edu.fiuba.algo3.modelo.UnidadesZerg.Zangano;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso2Test {

    @Test
    public void test01UnCriaderoNoEstaConstruidoEn3Turnos() {
        Criadero unCriadero = new Criadero();
        int turnosAPasar = 3;

        for (int i = 0; i < turnosAPasar; i++) {
            unCriadero.pasarTurno();
        }

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unCriadero.crearUnidad(new FabricaZangano()));
    }

    @Test
    public void test02UnCriaderoEstaConstruidoEn4Turnos() {
        Criadero unCriadero = new Criadero();
        int turnosAPasar = 4;

        for (int i = 0; i < turnosAPasar; i++) {
            unCriadero.pasarTurno();
        }

        assertDoesNotThrow(() -> unCriadero.crearUnidad(new FabricaZangano()));
    }

    @Test
    public void test03UnExtractorNoEstaConstruidoEn5Turnos() {
        Recurso gasDelImperio = new Recurso(0);
        Extractor unExtractor = new Extractor(gasDelImperio);

        int turnosAPasar = 5;

        for (int i = 0; i < turnosAPasar; i++) {
            unExtractor.pasarTurno();
        }

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unExtractor.contratarZangano(new Zangano()));
    }

    @Test
    public void test04UnExtractorEstaConstruidoEn6Turnos() {
        Zerg zerg = new Zerg();
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenadasGas = new Coordenada(0,0);
        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);

        zerg.construirCriadero(new Coordenada(1,1));
        for (int i = 0; i < 5; i++){
            zerg.terminarTurno();
        }

        zerg.construirExtractor(coordenadasGas);
        Edificio criadero = zerg.conseguirEdificio(new Coordenada(1,1));
        UnidadZerg zangano = criadero.crearUnidad(new FabricaZangano());

        //Construimos el extractor
        for (int i = 0; i < 6; i++){
            zerg.terminarTurno();
        }
        Edificio extractor = zerg.conseguirEdificio(coordenadasGas);
        assertDoesNotThrow( () -> extractor.contratarUnidad(zangano));
    }

    @Test
    public void test05UnaReservaDeReproduccionNoEstaConstruidaEn11Turnos(){
        ReservaDeReproduccion unaReserva = new ReservaDeReproduccion();
        int turnosAPasar = 11;

        for(int i = 0; i < turnosAPasar; i++) {
            unaReserva.pasarTurno();
        }

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unaReserva.crearFabricaZerling());
    }

     @Test
     public void test06UnaReservaDeReproduccionEstaConstruidaEn12Turnos() {
        ReservaDeReproduccion unaReserva = new ReservaDeReproduccion();
        int turnosAPasar = 12;

        for (int i = 0; i < turnosAPasar; i++) {
            unaReserva.pasarTurno();
        }

        assertDoesNotThrow(() -> unaReserva.crearFabricaZerling());
     }

    @Test
    public void test07UnaGuaridaNoEstaConstruidaEn11Turnos(){
        Guarida unaGuarida = new Guarida();
        int turnosAPasar = 11;

        for(int i = 0; i < turnosAPasar; i++) {
            unaGuarida.pasarTurno();
        }

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unaGuarida.crearFabricaHidralisco());
    }

    @Test
    public void test08UnaGuaridaEstaConstruidaEn12Turnos() {
        Guarida unaGuarida = new Guarida();
        int turnosAPasar = 12;

        for (int i = 0; i < turnosAPasar; i++) {
            unaGuarida.pasarTurno();
        }

        assertDoesNotThrow(() -> unaGuarida.crearFabricaHidralisco());
    }

    @Test
    public void test09UnEspiralNoEstaConstruidoEn9Turnos() {
        Espiral unEspiral = new Espiral();
        int turnosAPasar = 9;

        for (int i = 0; i < turnosAPasar; i++) {
            unEspiral.pasarTurno();
        }

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unEspiral.crearFabricaMutalisco());
    }

    @Test
    public void test10UnEspiralEstaConstruidoEn10Turnos() {
        Espiral unEspiral = new Espiral();
        int turnosAPasar = 10;

        for (int i = 0; i < turnosAPasar; i++) {
            unEspiral.pasarTurno();
        }

        assertDoesNotThrow(() -> unEspiral.crearFabricaMutalisco());
    }

    @Test
    public void test11UnNexoMineralNoEstaConstruidoEn3Turnos() {
        Protoss protoss = new Protoss();
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenadasMIneral = new Coordenada(0,0);

        mapa.colocarMaterial(new MineralRecolectable(), coordenadasMIneral);
        protoss.construirNexoMineral(coordenadasMIneral);
        for (int i = 0; i < 3; i++){
            protoss.terminarTurno();
        }
        assert(protoss.tienesEstaCantidadDeMineral(0));
    }

    @Test
    public void test12UnNexoMineralEstaConstruidoEn4Turnos() {
        Protoss protoss = new Protoss();
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenadaMineral = new Coordenada(0,0);

        mapa.colocarMaterial(new MineralRecolectable(), coordenadaMineral);
        protoss.construirNexoMineral(coordenadaMineral);
        for (int i = 0; i < 4; i++){
            protoss.terminarTurno();
        }
        assert(protoss.tienesEstaCantidadDeMineral(10));
    }

    @Test
    public void test13UnPilonNoEstaConstruidoEn4TUrnos() {
        Mapa elmapa = Mapa.obtener();
        elmapa.reiniciarMapa();

        Coordenada coordenadasPilon = new Coordenada(2,2);
        Pilon unpilon = new Pilon();
        elmapa.construirEdificio(unpilon, coordenadasPilon);

        Acceso unAcceso = new Acceso();
        Coordenada coordenadaAcceso = new Coordenada(2,3);

        int turnosAPasar = 4;

        for (int i = 0; i < turnosAPasar; i++) {
            unpilon.pasarTurno();
        }

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elmapa.construirEdificio(unAcceso, coordenadaAcceso));
    }

    @Test
    public void test14UnPilonEstaConstruidoEn5TUrnos() {
        Mapa elmapa = Mapa.obtener();
        elmapa.reiniciarMapa();

        Coordenada coordenadasPilon = new Coordenada(2,2);
        Pilon unpilon = new Pilon();
        elmapa.construirEdificio(unpilon, coordenadasPilon);

        Acceso unAcceso = new Acceso();
        Coordenada coordenadaAcceso = new Coordenada(2,3);

        int turnosAPasar = 5;

        for (int i = 0; i < turnosAPasar; i++) {
            unpilon.pasarTurno();
        }

        assertDoesNotThrow(() -> elmapa.construirEdificio(unAcceso, coordenadaAcceso));
    }

    @Test
    public void test15UnAsimiladorNoEstaConstruidoEn5Turnos() {
        Protoss protoss = new Protoss();
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenadasGas = new Coordenada(0,0);

        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);
        protoss.construirAsimilador(coordenadasGas);
        for (int i = 0; i < 5; i++){
            protoss.terminarTurno();
        }
        assert(protoss.tienesEstaCantidadDeMineral(0));
    }

    @Test
    public void test16UnAsimiladorEstaConstruidoEn6Turnos() {
        Protoss protoss = new Protoss();
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenadasGas = new Coordenada(0,0);

        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);
        protoss.construirAsimilador(coordenadasGas);
        for (int i = 0; i < 6; i++){
            protoss.terminarTurno();
        }
        assert(protoss.tienesEstaCantidadDeGas(20));
    }

    @Test
    public void test17UnAccesoNoEstaConstruidoEn7Turnos() {
        Acceso unAcceso = new Acceso();
        int turnosAPasar = 7;

        for (int i = 0; i < turnosAPasar; i++) {
            unAcceso.pasarTurno();
        }

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unAcceso.crearFabricaDragon());
    }

    @Test
    public void test18UnAccesoEstaConstruidoEn8Turnos() {
        Acceso unAcceso = new Acceso();
        int turnosAPasar = 8;

        for (int i = 0; i < turnosAPasar; i++) {
            unAcceso.pasarTurno();
        }

        assertDoesNotThrow(() -> unAcceso.crearFabricaDragon());
    }

    @Test
    public void test19UnPuertoEstelarNoEstaConstruidoEn9Turnos() {
        PuertoEstelar unPuertoEstelar = new PuertoEstelar();
        int turnosAPasar = 9;

        for (int i = 0; i < turnosAPasar; i++) {
            unPuertoEstelar.pasarTurno();
        }

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unPuertoEstelar.crearFabricaScout());
    }

    @Test
    public void test20UnPuertoEstelarEstaConstruidoEn10Turnos() {
        PuertoEstelar unPuertoEstelar = new PuertoEstelar();
        int turnosAPasar = 10;

        for (int i = 0; i < turnosAPasar; i++) {
            unPuertoEstelar.pasarTurno();
        }

        assertDoesNotThrow(() -> unPuertoEstelar.crearFabricaScout());
    }
}
