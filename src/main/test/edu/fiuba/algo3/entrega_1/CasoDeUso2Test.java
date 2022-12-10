package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.*;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.*;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.*;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaAcceso;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaAsimilador;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaNexoMineral;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.*;
import edu.fiuba.algo3.modelo.Excepciones.*;
import edu.fiuba.algo3.modelo.Imperio.*;
import edu.fiuba.algo3.modelo.Mapa.*;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CasoDeUso2Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01UnCriaderoNoEstaConstruidoEn3Turnos() {
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(200), new Gas(0));
        imperioZerg.construirEdificio(new FabricaCriadero(),new Coordenada(0,0));

        int turnosParaConstruir = 3;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio unCriadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unCriadero.crearUnidad(new FabricaZangano()));
    }

    @Test
    public void test02UnCriaderoEstaConstruidoEn4Turnos() {
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(200), new Gas(0));
        imperioZerg.construirEdificio(new FabricaCriadero(), new Coordenada(0,0));

        int turnosParaConstruir = 4;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioZerg.terminarTurno();

        assertTrue(imperioZerg.tieneEdificio(new Criadero()));
    }

    @Test
    public void test03UnExtractorNoEstaConstruidoEn5Turnos() {
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos(new Mineral(500), new Gas(0));

        Mapa elMapa = Mapa.obtener();
        elMapa.colocarMaterial(new GasRecolectable(), new Coordenada(1,0));

        // Construyo criadero para generar moho
        imperioZerg.construirEdificio(new FabricaCriadero(), new Coordenada(0,0));
        for(int i = 0; i < 4; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirEdificio(new FabricaExtractor(), new Coordenada(1,0));

        int turnosParaConstruir = 5;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio unExtractor = imperioZerg.conseguirEdificio(new Coordenada(1,0));

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unExtractor.contratarUnidad(new Zangano()));
    }

    @Test
    public void test04UnExtractorEstaConstruidoEn6Turnos() {
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos(new Mineral(500), new Gas(0));

        Mapa elMapa = Mapa.obtener();
        elMapa.colocarMaterial(new GasRecolectable(), new Coordenada(1,0));

        // Construyo criadero para generar moho
        imperioZerg.construirEdificio(new FabricaCriadero(), new Coordenada(0,0));
        for(int i = 0; i < 4; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirEdificio(new FabricaExtractor(), new Coordenada(1,0));

        int turnosParaConstruir = 6;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioZerg.terminarTurno();

        assertTrue(imperioZerg.tieneEdificio(new Extractor(new Gas(200))));
    }

    @Test
    public void test05UnaReservaDeReproduccionNoEstaConstruidaEn11Turnos(){
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos(new Mineral(500), new Gas(0));

        // Construyo criadero para generar moho
        imperioZerg.construirEdificio(new FabricaCriadero(), new Coordenada(0,0));
        for(int i = 0; i < 4; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), new Coordenada(1,0));

        int turnosParaConstruir = 11;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioZerg.terminarTurno();

        Edificio unCriadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> unCriadero.crearUnidad(new FabricaZerling()));
    }

     @Test
     public void test06UnaReservaDeReproduccionEstaConstruidaEn12Turnos() {
         Zerg imperioZerg = new Zerg();
         imperioZerg.abastecerDeRecursos(new Mineral(500), new Gas(0));

         // Construyo criadero para generar moho
         imperioZerg.construirEdificio(new FabricaCriadero(), new Coordenada(0,0));
         for(int i = 0; i < 4; i++)
             imperioZerg.terminarTurno();

         imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), new Coordenada(1,0));

         int turnosParaConstruir = 11;
         for(int i = 0; i < turnosParaConstruir; i++)
             imperioZerg.terminarTurno();

         Edificio unCriadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));

         assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                 () -> unCriadero.crearUnidad(new FabricaZerling()));
     }

    @Test
    public void test07UnaGuaridaNoEstaConstruidaEn11Turnos(){
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos(new Mineral(1000), new Gas(1000));

        // Construyo criadero para generar moho
        imperioZerg.construirEdificio(new FabricaCriadero(), new Coordenada(0,0));
        for(int i = 0; i < 4; i++)
            imperioZerg.terminarTurno();

        // Construyo una reserva de reproduccion para el prerequisito
        imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), new Coordenada(0,1));

        imperioZerg.construirEdificio(new FabricaGuarida(), new Coordenada(1,0));

        int turnosParaConstruir = 11;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioZerg.terminarTurno();

        Edificio unCriadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> unCriadero.crearUnidad(new FabricaHidralisco()));
    }

    @Test
    public void test08UnaGuaridaEstaConstruidaEn12Turnos() {
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos(new Mineral(1000), new Gas(1000));

        // Construyo criadero para generar moho
        imperioZerg.construirEdificio(new FabricaCriadero(), new Coordenada(0,0));
        for(int i = 0; i < 4; i++)
            imperioZerg.terminarTurno();

        // Construyo una reserva de reproduccion para el prerequisito
        imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), new Coordenada(0,1));

        imperioZerg.construirEdificio(new FabricaGuarida(), new Coordenada(1,0));

        int turnosParaConstruir = 12;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioZerg.terminarTurno();

        assertTrue(imperioZerg.tieneEdificio(new Guarida()));
    }

    @Test
    public void test09UnEspiralNoEstaConstruidoEn9Turnos() {
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos(new Mineral(1000), new Gas(1000));

        // Construyo criadero para generar moho
        imperioZerg.construirEdificio(new FabricaCriadero(), new Coordenada(0,0));
        for(int i = 0; i < 4; i++)
            imperioZerg.terminarTurno();

        // Construyo una reserva de reproduccion y guarida para el prerequisito
        imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), new Coordenada(0,1));
        imperioZerg.construirEdificio(new FabricaGuarida(), new Coordenada(0,2));

        imperioZerg.construirEdificio(new FabricaEspiral(), new Coordenada(1,0));

        int turnosParaConstruir = 9;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioZerg.terminarTurno();

        Edificio unCriadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> unCriadero.crearUnidad(new FabricaMutalisco()));
    }

    @Test
    public void test10UnEspiralEstaConstruidoEn10Turnos() {
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos(new Mineral(1000), new Gas(1000));

        // Construyo criadero para generar moho
        imperioZerg.construirEdificio(new FabricaCriadero(), new Coordenada(0,0));
        for(int i = 0; i < 4; i++)
            imperioZerg.terminarTurno();

        // Construyo una reserva de reproduccion para el prerequisito
        imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), new Coordenada(0,1));
        imperioZerg.construirEdificio(new FabricaGuarida(), new Coordenada(0,2));

        imperioZerg.construirEdificio(new FabricaEspiral(), new Coordenada(1,0));

        int turnosParaConstruir = 10;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioZerg.terminarTurno();

        assertTrue(imperioZerg.tieneEdificio(new Guarida()));
    }

    @Test
    public void test11UnNexoMineralNoEstaConstruidoEn3Turnos() {
        Protoss imperioProtoss = new Protoss();
        imperioProtoss.abastecerDeRecursos(new Mineral(50), new Gas(0));
        Coordenada coordenada = new Coordenada(1,0);

        Mapa elMapa = Mapa.obtener();
        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        imperioProtoss.construirEdificio(new FabricaNexoMineral(), coordenada);

        int turnosParaConstruir = 3;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioProtoss.terminarTurno();

        assertTrue(imperioProtoss.tienesEstaCantidadDeMineral(0));
    }

    @Test
    public void test12UnNexoMineralEstaConstruidoEn4Turnos() {
        Protoss imperioProtoss = new Protoss();
        imperioProtoss.abastecerDeRecursos(new Mineral(50), new Gas(0));
        Coordenada coordenada = new Coordenada(1, 0);

        Mapa elMapa = Mapa.obtener();
        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        imperioProtoss.construirEdificio(new FabricaNexoMineral(), coordenada);

        int turnosParaConstruir = 4;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioProtoss.terminarTurno();

        assertTrue(imperioProtoss.tienesEstaCantidadDeMineral(10));
    }

    @Test
    public void test13UnPilonNoEstaConstruidoEn4TUrnos() {
        Protoss imperioProtoss = new Protoss();
        imperioProtoss.abastecerDeRecursos(new Mineral(1000), new Gas(1000));

        imperioProtoss.construirEdificio(new FabricaPilon(), new Coordenada(1,0));

        int turnosParaConstruir = 4;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioProtoss.terminarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> imperioProtoss.construirEdificio(new FabricaAcceso(), new Coordenada(0,0)));
    }

    @Test
    public void test14UnPilonEstaConstruidoEn5TUrnos() {
        Protoss imperioProtoss = new Protoss();
        imperioProtoss.abastecerDeRecursos(new Mineral(1000), new Gas(1000));

        imperioProtoss.construirEdificio(new FabricaPilon(), new Coordenada(1,0));

        int turnosParaConstruir = 5;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioProtoss.terminarTurno();

        assertTrue(imperioProtoss.tieneEdificio(new Pilon()));
    }

    @Test
    public void test15UnAsimiladorNoEstaConstruidoEn5Turnos() {
        Protoss imperioProtoss = new Protoss();
        imperioProtoss.abastecerDeRecursos(new Mineral(100), new Gas(0));
        Coordenada coordenadasGas = new Coordenada(1, 0);

        Mapa elMapa = Mapa.obtener();
        elMapa.colocarMaterial(new GasRecolectable(), coordenadasGas);

        imperioProtoss.construirEdificio(new FabricaAsimilador(), coordenadasGas);

        int turnosParaConstruir = 5;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioProtoss.terminarTurno();

        assertTrue(imperioProtoss.tienesEstaCantidadDeGas(0));
    }

    @Test
    public void test16UnAsimiladorEstaConstruidoEn6Turnos() {
        Protoss imperioProtoss = new Protoss();
        imperioProtoss.abastecerDeRecursos(new Mineral(100), new Gas(0));
        Coordenada coordenadasGas = new Coordenada(1, 0);

        Mapa elMapa = Mapa.obtener();
        elMapa.colocarMaterial(new GasRecolectable(), coordenadasGas);

        imperioProtoss.construirEdificio(new FabricaAsimilador(), coordenadasGas);

        int turnosParaConstruir = 6;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioProtoss.terminarTurno();

        assertTrue(imperioProtoss.tienesEstaCantidadDeGas(20));
    }

    @Test
    public void test17UnAccesoNoEstaConstruidoEn7Turnos() {
        Protoss imperioProtoss = new Protoss();
        imperioProtoss.abastecerDeRecursos(new Mineral(1000), new Gas(1000));

        // construyo pilon para generar energia
        imperioProtoss.construirEdificio(new FabricaPilon(), new Coordenada(0,0));
        for(int i = 0; i < 5; i++)
            imperioProtoss.terminarTurno();

        imperioProtoss.construirEdificio(new FabricaAcceso(), new Coordenada(1,0));
        int turnosParaConstruir = 7;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioProtoss.terminarTurno();

        Edificio unAcceso = imperioProtoss.conseguirEdificio(new Coordenada(1,0));

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unAcceso.crearUnidad(new FabricaDragon()));
    }

    @Test
    public void test18UnAccesoEstaConstruidoEn8Turnos() {
        Protoss imperioProtoss = new Protoss();
        imperioProtoss.abastecerDeRecursos(new Mineral(1000), new Gas(1000));

        // construyo pilon para generar energia
        imperioProtoss.construirEdificio(new FabricaPilon(), new Coordenada(0,0));
        for(int i = 0; i < 5; i++)
            imperioProtoss.terminarTurno();

        imperioProtoss.construirEdificio(new FabricaAcceso(), new Coordenada(1,0));
        int turnosParaConstruir = 8;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioProtoss.terminarTurno();

        assertTrue(imperioProtoss.tieneEdificio(new Acceso()));
    }

    @Test
    public void test19UnPuertoEstelarNoEstaConstruidoEn9Turnos() {
        Protoss imperioProtoss = new Protoss();
        imperioProtoss.abastecerDeRecursos(new Mineral(1000), new Gas(1000));

        // construyo pilon para generar energia
        imperioProtoss.construirEdificio(new FabricaPilon(), new Coordenada(0,0));
        for(int i = 0; i < 5; i++)
            imperioProtoss.terminarTurno();

        // construyo un acceso para el prerequisito
        imperioProtoss.construirEdificio(new FabricaAcceso(), new Coordenada(0,1));

        imperioProtoss.construirEdificio(new FabricaPuertaEstelar(), new Coordenada(1,0));
        int turnosParaConstruir = 9;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioProtoss.terminarTurno();

        Edificio puertoEstelar = imperioProtoss.conseguirEdificio(new Coordenada(1,0));

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> puertoEstelar.crearUnidad(new FabricaScout()));
    }

    @Test
    public void test20UnPuertoEstelarEstaConstruidoEn10Turnos() {
        Protoss imperioProtoss = new Protoss();
        imperioProtoss.abastecerDeRecursos(new Mineral(1000), new Gas(1000));

        // construyo pilon para generar energia
        imperioProtoss.construirEdificio(new FabricaPilon(), new Coordenada(0,0));
        for(int i = 0; i < 5; i++)
            imperioProtoss.terminarTurno();

        // construyo un acceso para el prerequisito
        imperioProtoss.construirEdificio(new FabricaAcceso(), new Coordenada(0,1));

        imperioProtoss.construirEdificio(new FabricaPuertaEstelar(), new Coordenada(1,0));
        int turnosParaConstruir = 10;
        for(int i = 0; i < turnosParaConstruir; i++)
            imperioProtoss.terminarTurno();

        assertTrue(imperioProtoss.tieneEdificio(new PuertoEstelar()));
    }
}
