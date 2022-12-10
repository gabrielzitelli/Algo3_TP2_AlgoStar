package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaAsimilador;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaNexoMineral;
import edu.fiuba.algo3.modelo.Excepciones.*;
import edu.fiuba.algo3.modelo.Imperio.*;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso8Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01SiNoTengoLosRecursosNecesariosNoPuedoConstruirUnCriadero() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos(new Mineral(0), new Gas(0));

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class ,
                () ->imperioZerg.construirCriadero(new Coordenada(0,0)));
    }

    @Test
    public void test02TengoLosRecursosNecesariosPuedoConstruirUnCriadero() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos();

        assertDoesNotThrow(() -> imperioZerg.construirCriadero(new Coordenada(0,0)));
    }

    @Test
    public void test03SiNoTengoLosRecursosNecesariosNoPuedoConstruirUnaReserva() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos(new Mineral(0), new Gas(0));

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class ,
                () ->imperioZerg.construirReservaDeReproduccion(new Coordenada(0,0)));
    }

    @Test
    public void test04TengoLosRecursosNecesariosPuedoConstruirUnaReserva() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> imperioZerg.construirReservaDeReproduccion(new Coordenada(0,0)));
    }

    @Test
    public void test05SiNoTengoLosRecursosNecesariosNoPuedoConstruirUnExtractor() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos(new Mineral(0), new Gas(0));

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class ,
                () ->imperioZerg.construirExtractor(new Coordenada(0,0)));
    }

    @Test
    public void test06TengoLosRecursosNecesariosPuedoConstruirUnExtractor() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> imperioZerg.construirExtractor(new Coordenada(0,0)));
    }

    @Test
    public void test07NOTengoLosRecursosNecesariosNOPuedoConstruirUnaGuarida(){
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(350), new Gas(0));
        imperioZerg.construirCriadero(new Coordenada(0,0));

        for (int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirReservaDeReproduccion(new Coordenada(0,1));

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> imperioZerg.construirGuarida(new Coordenada(1,1)));
    }

    @Test
    public void test08TengoLosRecursosNecesariosPuedoConstruirUnaGuarida(){
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos();
        imperioZerg.construirCriadero(new Coordenada(0,0));

        for (int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirReservaDeReproduccion(new Coordenada(0,1));

        assertDoesNotThrow(() ->imperioZerg.construirGuarida(new Coordenada(1,1)));
    }

    @Test
    public void test09NOTengoLosRecursosNecesariosNOPuedoConstruirUnEspiral(){
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(550), new Gas(100));
        imperioZerg.construirCriadero(new Coordenada(0,0));

        for (int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirReservaDeReproduccion(new Coordenada(0,1));
        imperioZerg.construirGuarida(new Coordenada(1,0));

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> imperioZerg.construirEspiral(new Coordenada(1,1)));
    }

    @Test
    public void test10TengoLosRecursosNecesariosPuedoConstruirUnEspiral(){
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos();
        imperioZerg.construirCriadero(new Coordenada(0,0));

        for (int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirReservaDeReproduccion(new Coordenada(0,1));
        imperioZerg.construirGuarida(new Coordenada(1,0));

        assertDoesNotThrow(() ->imperioZerg.construirEspiral(new Coordenada(1,1)));
    }

    @Test
    public void test11NoTengoLosRecursosNecesariosNoPuedoConstruirUnNexoMineral() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(0), new Gas(0));
        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirEdificio(new FabricaNexoMineral(), coordenada));
    }

    @Test
    public void test12TengoLosRecursosNecesariosPuedoConstruirUnNexoMineral() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos();
        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        assertDoesNotThrow(() -> protoss.construirEdificio(new FabricaNexoMineral(), coordenada));
    }

    @Test
    public void test13NoTengoLosRecursosNecesariosNoPuedoConstruirUnAsimilador() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(0), new Gas(0));
        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirEdificio(new FabricaAsimilador(), coordenada));
    }

    @Test
    public void test14TengoLosRecursosNecesariosPuedoConstruirUnAsimilador() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos();
        elMapa.colocarMaterial(new GasRecolectable(), coordenada);

        assertDoesNotThrow(() -> protoss.construirEdificio(new FabricaAsimilador(), coordenada));
    }

    @Test
    public void test15NoTengoLosRecursosNecesariosNoPuedoConstruirUnPilon() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(0), new Gas(0));

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirPilon(coordenada));
    }

    @Test
    public void test16TengoLosRecursosNecesariosPuedoConstruirUnPilon() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos();

        assertDoesNotThrow(() -> protoss.construirPilon(coordenada));
    }

    @Test
    public void test17NoTengoLosRecursosNecesariosNoPuedoConstruirUnAcceso() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(100), new Gas(0));
        protoss.construirPilon(coordenada);

        for(int i = 0; i < 5; i++)
            protoss.terminarTurno();

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirAcceso(new Coordenada(1,1)));
    }

    @Test
    public void test18TengoLosRecursosNecesariosPuedoConstruirUnPilon() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(250), new Gas(0));
        protoss.construirPilon(coordenada);
        for(int i = 0; i < 6; i++)
            protoss.terminarTurno();

        assertDoesNotThrow(() -> protoss.construirAcceso(new Coordenada(0,1)));
    }

    @Test
    public void test19NoTengoLosRecursosNecesariosNoPuedoConstruirUnPuertoEstelar() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(250), new Gas(0));
        protoss.construirPilon(coordenada);

        for(int i = 0; i < 5; i++)
            protoss.terminarTurno();

        protoss.construirAcceso(new Coordenada(1,0));

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirPuertoEstelar(new Coordenada(1,1)));
    }

    @Test
    public void test20TengoLosRecursosNecesariosPuedoConstruirUnPuertoEstelar() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(400), new Gas(150));
        protoss.construirPilon(coordenada);

        for(int i = 0; i < 6; i++)
            protoss.terminarTurno();

        protoss.construirAcceso(new Coordenada(1,0));

        assertDoesNotThrow(() -> protoss.construirPuertoEstelar(new Coordenada(0,1)));
    }
}
